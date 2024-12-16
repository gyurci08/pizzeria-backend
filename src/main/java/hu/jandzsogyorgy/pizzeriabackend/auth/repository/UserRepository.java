package hu.jandzsogyorgy.pizzeriabackend.auth.repository;

import hu.jandzsogyorgy.pizzeriabackend.auth.entities.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
    User findByUsername(String username);
}
