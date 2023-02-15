package hello.geulenobi.service;

import hello.geulenobi.domain.EmailUserRepository;
import hello.geulenobi.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final EmailUserRepository emailUserRepository;

    /**
     * @return null 로그인 실패
     */
    public User login(String email, String password) {
        return emailUserRepository.findByEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}