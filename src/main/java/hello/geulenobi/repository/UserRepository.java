package hello.geulenobi.repository;

import hello.geulenobi.domain.User;

import java.util.List;
import java.util.Optional;

//TODO JpaRepo로 바꾸기

public interface UserRepository {
    User save(User member);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
    List<User> findAll();
}