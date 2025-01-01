package hu.jandzsogyorgy.pizzeriabackend.auth.repository;

import hu.jandzsogyorgy.pizzeriabackend.auth.entities.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
    // TODO: Query both at the same time?
    User findByUsername(String username);

    User findByEmail(String email);

    User findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
