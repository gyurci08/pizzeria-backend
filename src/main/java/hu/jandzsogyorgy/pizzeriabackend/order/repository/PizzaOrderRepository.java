package hu.jandzsogyorgy.pizzeriabackend.order.repository;

import hu.jandzsogyorgy.pizzeriabackend.order.entity.PizzaOrder;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaOrderRepository extends ListCrudRepository<PizzaOrder, Long> {
}
