package hu.jandzsogyorgy.pizzeriabackend.feature.customer.controller;

import hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto.CustomerDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.service.CustomerService;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.dto.MenuItemDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.service.MenuItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//
@RestController
@RequestMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDto> listCustomers() {
        return customerService.listCustomers();
    }

}
