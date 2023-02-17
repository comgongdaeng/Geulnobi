package hello.geulenobi.service;

import hello.geulenobi.domain.User;
import hello.geulenobi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private final UserRepository userRepository;

/*    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    } //동적 변경 차단

    @Override
    public User register(User user) {
        
        validateDuplicateUser(user);//이메일로 검색. 없으면 제대로 동작. 있으면 예외발생.
       /* user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));//비밀번호 암호화해서 저장
        다른거 제대로 동작하는지 보고 추가할 예정. JWT 고려 */
        userRepository.save(user);

        return user;
        }

    private void validateDuplicateUser(User user) { //중복검사
        userRepository.findByEmail(user.getEmail())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<User> findOne(String email){
        return userRepository.findByEmail(email);
    }
    @Override
    public User login(String email, String password) {
        return userRepository.findByEmail(email).filter(m-> m.getPassword().equals(password)).orElse(null);
    }
}