package hu.jandzsogyorgy.pizzeriabackend.feature.order.service;

import hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto.CustomerDto;
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

    private final CustomerService customerService;

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


    // load order

    //new order
    @Transactional
    public OrderWithItemsDto createOrder(OrderSaveDto dto) {
        List<Long> itemIds = dto.orderItems().stream().map(OrderItemSaveDto::menuItemId).toList();
        List<MenuItem> foundItems = menuItemMapper.toEntity(menuItemService.listMenuItemsByIds(itemIds));

        BigDecimal totalAmount = foundItems.stream()
                .flatMap(                                                   // combines subtotal calculations into a single stream
                        foundItem ->
                                dto.orderItems()
                                        .stream()
                                        .filter(
                                                itemDto -> itemDto.menuItemId().equals(foundItem.getId())
                                        )
                                        .map(
                                                itemDto -> foundItem.getPrice().multiply(BigDecimal.valueOf(itemDto.quantity()))
                                        )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add); // summing them up, starting with BigDecimal.ZERO as the initial value

        Order order = orderSaveMapper.toEntity(dto);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(totalAmount);
        order.setStatus(Status.PLACED);

        Order savedOrder = orderRepository.save(order);

        List<OrderItemDto> items = dto.orderItems().stream()
                .map(itemDto -> orderItemService.createOrderItem(itemDto, savedOrder.getId()))
                .toList();

        return orderMapper.toDtoWithItems(savedOrder, orderItemMapper.toEntity(items));
    }


    // edit order
    // remove order
}
