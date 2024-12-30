package hu.jandzsogyorgy.pizzeriabackend.feature.customer.service;

import hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto.CustomerDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.entity.Customer;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.map.CustomerMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private Customer findById(Long id){
        return customerRepository.findById(id).orElse(null);
    }

    public CustomerDto loadCustomer(Long id){
        return customerMapper.toDto(findById(id));
    }

}
