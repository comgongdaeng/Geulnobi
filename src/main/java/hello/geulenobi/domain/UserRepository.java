package hello.geulenobi.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    //user의 pk가 email...이면 안되는데... DB 설계 및 코드 수정 필요... TODO
    Optional<User> findByEmail(String email);

}
