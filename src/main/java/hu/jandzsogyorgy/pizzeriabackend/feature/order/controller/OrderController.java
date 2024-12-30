package hu.jandzsogyorgy.pizzeriabackend.feature.order.controller;

import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderWithItemsDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderWithNameDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//
@RestController
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    //@PreAuthorize("hasRole('ADMIN')")  // Method level authentication
    @GetMapping
    public List<OrderDto> listOrders() {
        return orderService.listOrders();
    }

    @GetMapping("/with-customer-names")
    public List<OrderWithNameDto> listOrdersWithCustomerNames() {
        return orderService.listOrdersWithName();
    }

    @GetMapping("/with-items")
    public List<OrderWithItemsDto> listOrdersWithItems() {
        return orderService.listOrdersWithItems();
    }


    @PutMapping
    public OrderWithItemsDto createOrder(@RequestBody OrderSaveDto dto) {
        return orderService.createOrder(dto);
    }


}
