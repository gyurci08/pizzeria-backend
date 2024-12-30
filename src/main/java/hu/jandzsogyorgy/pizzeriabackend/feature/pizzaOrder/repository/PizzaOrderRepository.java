package hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.repository;

import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaOrder.entity.PizzaOrder;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaOrderRepository extends ListCrudRepository<PizzaOrder, Long> {
}
