package hu.jandzsogyorgy.pizzeriabackend.auth.repository;

import hu.jandzsogyorgy.pizzeriabackend.auth.entities.Role;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ListCrudRepository<Role, Long> {
}
