package hu.jandzsogyorgy.pizzeriabackend.feature.order.repository;

import hu.jandzsogyorgy.pizzeriabackend.feature.order.entity.Order;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ListCrudRepository<Order, Long> {
}
