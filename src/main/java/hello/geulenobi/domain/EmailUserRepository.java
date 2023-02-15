package hello.geulenobi.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class EmailUserRepository {

    private static Map<Long, User> store = new HashMap<>(); //static 사용
    private static long sequence = 0L;//static 사용

    public User save(User member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public User findById(Long id) {
        return store.get(id);
    }

    public Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(m -> m.getEmail().equals(email))
                .findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

