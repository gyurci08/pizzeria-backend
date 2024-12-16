package hu.jandzsogyorgy.pizzeriabackend.auth.repository;

import hu.jandzsogyorgy.pizzeriabackend.auth.entities.UserRole;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends ListCrudRepository<UserRole, Long> {
    List<UserRole> findAllByUserId(Long id);
}
