package hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.repository;

import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.entity.MenuItem;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends ListCrudRepository<MenuItem, Long> {
}
