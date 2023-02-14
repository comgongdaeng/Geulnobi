package hello.glogin.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    //user의 pk가 Long 타입 id임
    Optional<User> findByEmail(String email);
}
