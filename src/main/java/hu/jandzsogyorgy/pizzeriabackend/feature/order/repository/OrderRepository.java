package hu.jandzsogyorgy.pizzeriabackend.feature.order.repository;

import hu.jandzsogyorgy.pizzeriabackend.feature.order.entity.Order;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends ListCrudRepository<Order, Long> {
    List<Order> findAllByCustomerId(Long customerId);
}
