package hu.jandzsogyorgy.pizzeriabackend.feature.order.controller;

import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.dto.OrderWithItemsDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//
@RestController
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> listOrders(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return orderService.listMyOrders(userDetails.getUsername());
    }

    @PostMapping
    public OrderWithItemsDto createOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody OrderSaveDto dto
    ) {
        return orderService.createMyOrder(userDetails.getUsername(), dto);
    }


}
