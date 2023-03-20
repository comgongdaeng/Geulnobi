package hello.geulenobi.service;


import hello.geulenobi.domain.User;
import hello.geulenobi.repository.GoogleUserRepository;
import hello.geulenobi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
//TODO repo에 save로 구현했던것들 service의 register로 처리하는 방법 고민해보기.

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final GoogleUserRepository googleUserRepository;
    //TODO 왜 쓰려 했더라?
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;



//TODO 비밀번호 암호화
// TODO 구글 로그인의 경우, 토큰처리하는게 더 좋은 방법인지?

    public UserServiceImpl(UserRepository userRepository, GoogleUserRepository googleUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.googleUserRepository = googleUserRepository;
        this.passwordEncoder = passwordEncoder;
    } //동적 변경 차단

    @Override
    public User register(User user) {
        
        validateDuplicateUser(user);//이메일로 검색. 없으면 제대로 동작. 있으면 예외발생.

        user.setPassword(passwordEncoder.encode(user.getPassword()));

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
    //내가 이걸 왜 만들었더라......

    @Override
    public User login(String email, String password) {
        //TODO LoginForm을 userDTO로 만들어야할지도?

        //자바 스트림으로 변경
        return userRepository.findByEmail(email).filter(user -> passwordEncoder.matches(password,user.getPassword())).orElse(null);

    }
}