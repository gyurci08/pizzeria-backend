package hu.jandzsogyorgy.pizzeriabackend.feature.pizzaIngredient.repository;

import hu.jandzsogyorgy.pizzeriabackend.feature.pizzaIngredient.entity.PizzaIngredient;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaIngredientRepository extends ListCrudRepository<PizzaIngredient, Long> {
}
