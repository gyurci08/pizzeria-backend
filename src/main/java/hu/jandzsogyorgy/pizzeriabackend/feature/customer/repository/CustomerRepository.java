package hu.jandzsogyorgy.pizzeriabackend.feature.customer.repository;

import hu.jandzsogyorgy.pizzeriabackend.feature.customer.entity.Customer;
import org.springframework.data.repository.ListCrudRepository;

public interface CustomerRepository extends ListCrudRepository<Customer, Long> {
    Customer findByUserId(Long userId);
}
