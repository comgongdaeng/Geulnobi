package hello.geulenobi.repository;

import hello.geulenobi.domain.GoogleUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoogleUserRepository extends JpaRepository<GoogleUser, Long> {
    GoogleUser save(GoogleUser member);
    Optional<GoogleUser> findById(Long id);
    Optional<GoogleUser> findByEmail(String email);
    Optional<GoogleUser> findByName(String name);
    List<GoogleUser> findAll();
    //구현체 만들어야하나? JPA는 안했던것 같은데... 만약해야되는거면 하기/ 아니라면 UserRepo interface도 수정하기
    //되네... TODO userRepo도 수정하기
}
