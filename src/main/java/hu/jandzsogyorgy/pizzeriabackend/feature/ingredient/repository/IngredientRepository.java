package hu.jandzsogyorgy.pizzeriabackend.feature.ingredient.repository;

import hu.jandzsogyorgy.pizzeriabackend.feature.ingredient.entity.Ingredient;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends ListCrudRepository<Ingredient, Long> {
}
