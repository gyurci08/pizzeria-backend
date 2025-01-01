package hu.jandzsogyorgy.pizzeriabackend.feature.customer.service;

import hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto.CustomerDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.dto.CustomerSaveDto;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.entity.Customer;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.map.CustomerMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.map.CustomerSaveMapper;
import hu.jandzsogyorgy.pizzeriabackend.feature.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerSaveMapper customerSaveMapper;

    private Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<CustomerDto> listCustomers() {
        return customerMapper.toDto(customerRepository.findAll());
    }

    public CustomerDto loadCustomer(Long id) {
        return customerMapper.toDto(findById(id));
    }

    public List<CustomerDto> loadCustomersByIds(Iterable<Long> ids) {
        return customerMapper.toDto(customerRepository.findAllById(ids));
    }

    @Transactional
    public CustomerDto createCustomer(CustomerSaveDto customerSaveDto) {
        return customerMapper.toDto(customerRepository.save(customerSaveMapper.toEntity(customerSaveDto)));
    }


    public CustomerDto loadCustomerByUserId(Long id) {
        return customerMapper.toDto(customerRepository.findByUserId(id));
    }


}
