package hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.repository;

import hu.jandzsogyorgy.pizzeriabackend.feature.menuItem.entity.MenuItem;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends ListCrudRepository<MenuItem, Long> {
    @NonNull
    @Override
    List<MenuItem> findAllById(Iterable<Long> ids);
}
