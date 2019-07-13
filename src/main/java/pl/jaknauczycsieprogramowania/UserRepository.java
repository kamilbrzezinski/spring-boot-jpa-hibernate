package pl.jaknauczycsieprogramowania;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);
    List<User> findByUsernameContaining(String username);
    List<User> findByAgeGreaterThan(int age);
    List<User> findByCityIn(Collection<String> cities);
}
