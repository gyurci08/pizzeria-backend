package hu.jandzsogyorgy.pizzeriabackend.pizzeria.controller;

import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.dto.PizzaOrderDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.dto.PizzaOrderSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.pizzeria.service.PizzeriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PizzeriaController {
    private final PizzeriaService pizzeriaService;




    //@PreAuthorize("hasRole('ADMIN')")  // Method level authentication
    @GetMapping("/orders")
    public List<PizzaOrderDto> listOrders() {
        return pizzeriaService.listOrders();
    }
    @PutMapping("/orders")
    public PizzaOrderDto createOrder(@RequestBody PizzaOrderSaveDto dto) {
        return pizzeriaService.createOrder(dto);
    }

}
