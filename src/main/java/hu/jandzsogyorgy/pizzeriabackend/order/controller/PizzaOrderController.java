package hu.jandzsogyorgy.pizzeriabackend.order.controller;

import hu.jandzsogyorgy.pizzeriabackend.order.PizzaOrderService;
import hu.jandzsogyorgy.pizzeriabackend.order.dto.PizzaOrderDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//
@RestController
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PizzaOrderController {
    private final PizzaOrderService pizzaOrderService;

    //@PreAuthorize("hasRole('ADMIN')")  // Method level authentication
    @GetMapping
    public List<PizzaOrderDto> listOrders() {
        return pizzaOrderService.listOrders();
    }
}