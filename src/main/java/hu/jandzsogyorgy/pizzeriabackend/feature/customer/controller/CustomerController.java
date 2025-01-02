package hu.jandzsogyorgy.pizzeriabackend.feature.customer.controller;

import hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto.CustomerDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto.CustomerSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

//
@RestController
@RequestMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public CustomerDto loadMyCustomer(@AuthenticationPrincipal UserDetails userDetails) {
        return customerService.loadMyCustomer(userDetails);
    }

    @PostMapping
    public CustomerDto saveMyCustomer(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CustomerSaveDto dto) {
        return customerService.saveMyCustomer(userDetails, dto);
    }

}
