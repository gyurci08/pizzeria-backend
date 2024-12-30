package hu.jandzsogyorgy.pizzeriabackend.feature.pizza.repository;

import hu.jandzsogyorgy.pizzeriabackend.feature.pizza.entity.Pizza;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends ListCrudRepository<Pizza, Long> {
}
