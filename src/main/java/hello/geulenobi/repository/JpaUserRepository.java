package hello.geulenobi.repository;

import hello.geulenobi.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }
    @Override
    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        List<User> results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }//pk가아니면 EntityManager의 find를 사용할 수 없음!


    @Override
    public Optional<User> findByName(String name) {

        List<User> result = em.createQuery("select m from User m where m.name=:name", User.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select m from Member m", User.class).getResultList();
    }

}
