package hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.service;

import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.dto.OrderItemDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.dto.OrderItemSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.entity.OrderItem;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.map.OrderItemMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.map.OrderItemSaveMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.orderItem.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemSaveMapper orderItemSaveMapper;


    public List<OrderItemDto> loadOrderItems() {
        return orderItemMapper.toDto(orderItemRepository.findAll());
    }

    public OrderItemDto createOrderItem(OrderItemSaveDto dto, Long orderId, BigDecimal price) {
        OrderItem orderItem = orderItemSaveMapper.toEntity(dto);
        orderItem.setOrderId(orderId);
        orderItem.setPrice(price);

        return orderItemMapper.toDto(
                orderItemRepository.save(orderItem)
        );
    }


}