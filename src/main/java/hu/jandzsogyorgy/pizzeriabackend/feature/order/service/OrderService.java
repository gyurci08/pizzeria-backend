package hu.jandzsogyorgy.pizzeriabackend.feature.order.service;

import hu.jandzsogyorgy.pizzeriabackend.auth.exception.AuthenticationException;
import hu.jandzsogyorgy.pizzeriabackend.auth.service.UserRoleService;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto.CustomerDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.map.CustomerMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.service.CustomerService;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.entity.MenuItem;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.map.MenuItemMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.service.MenuItemService;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderWithItemsDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderWithNameDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.entity.Order;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.entity.Status;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.map.OrderMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.map.OrderSaveMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.repository.OrderRepository;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.dto.OrderItemDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.dto.OrderItemSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.map.OrderItemMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderSaveMapper orderSaveMapper;

    private final OrderItemMapper orderItemMapper;
    private final OrderItemService orderItemService;

    private final MenuItemService menuItemService;
    private final MenuItemMapper menuItemMapper;

    private final UserRoleService userRoleService;
    private final CustomerService customerService;

    private final CustomerMapper customerMapper;


    private Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }


    private Map<Long, String> getCustomerIdToNameMap(List<Order> orders) {
        Set<Long> customerIds = orders.stream()
                .map(Order::getCustomerId)
                .collect(Collectors.toSet());

        return customerService.loadCustomersByIds(customerIds)
                .stream()
                .collect(Collectors.toMap(
                        CustomerDto::id,
                        CustomerDto::name,
                        (existing, replacement) -> existing
                ));
    }

    // list orders
    public List<OrderDto> listOrders() {
        return orderMapper.toDto(
                orderRepository.findAll()
        );
    }


    public List<OrderWithNameDto> listOrdersWithName() {
        List<Order> orders = orderRepository.findAll();
        Map<Long, String> customerIdNameMap = getCustomerIdToNameMap(orders);

        return orders.stream()
                .map(order -> orderMapper.toDtoWithName(order, customerIdNameMap.get(order.getCustomerId())))
                .toList();
    }


    public List<OrderWithItemsDto> listOrdersWithItems() {
        return orderRepository.findAll().stream().map(order -> {
            return orderMapper.toDtoWithItems(order, orderItemMapper.toEntity(orderItemService.loadOrderItems()));
        }).toList();
    }


    //new order
    @Transactional
    public OrderWithItemsDto createOrder(OrderSaveDto dto) {
        List<Long> itemIds = dto.orderItems().stream().map(OrderItemSaveDto::menuItemId).toList();
        List<MenuItem> foundItems = menuItemMapper.toEntity(menuItemService.listMenuItemsByIds(itemIds));


        BigDecimal totalAmount = dto.orderItems().stream()
                .map(
                        item ->
                        {
                            MenuItem menuItem = foundItems.stream()
                                    .filter(mi -> mi.getId().equals(item.menuItemId()))
                                    .findFirst()
                                    .orElseThrow(() -> new RuntimeException("Menu item not found"));
                            return menuItem.getPrice().multiply(BigDecimal.valueOf(item.quantity()));
                        })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = orderSaveMapper.toEntity(dto);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order.setStatus(Status.PLACED);

        Order savedOrder = orderRepository.save(order);

        List<OrderItemDto> items = dto.orderItems().stream()
                .map(itemSaveDto -> {
                    MenuItem menuItem = foundItems.stream()
                            .filter(item -> item.getId().equals(itemSaveDto.menuItemId()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Menu item not found"));

                    BigDecimal price = menuItem.getPrice().multiply(
                            BigDecimal.valueOf(itemSaveDto.quantity())
                    );

                    return orderItemService.createOrderItem(itemSaveDto, savedOrder.getId(), price);
                })
                .toList();


        return orderMapper.toDtoWithItems(savedOrder, orderItemMapper.toEntity(items));
    }

    @Transactional
    public OrderDto changeOrderStatus(Order order, Status status) {
        order.setStatus(status);
        return orderMapper.toDto(orderRepository.save(order));
    }


    // Customer's authority


    public List<OrderDto> listMyOrders(String username) {
        return orderMapper.toDto(
                orderRepository.findAllByCustomerId(userRoleService.getUserId(username))
        );
    }

    @Transactional
    public OrderWithItemsDto createMyOrder(String username, OrderSaveDto dto) {
        if (dto.customerId().equals(userRoleService.getUserId(username))) {
            return createOrder(dto);
        }
        throw new AuthenticationException("You are not authorized");
    }

    @Transactional
    public void deleteMyOrder(UserDetails userDetails, Long orderId) {
        Order order = findOrderById(orderId);
        CustomerDto customerDto = customerService.loadMyCustomer(userDetails);

        if (order.getCustomerId().equals(customerDto.id())) {
            changeOrderStatus(order, Status.DELETED);
        } else throw new AuthenticationException("You are not authorized");
    }

    // edit order
    // remove order
}
