package hello.glogin.service;

import hello.glogin.domain.User;
import hello.glogin.domain.UserRepository;
import hello.glogin.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        //userRequest를 받아옴


        //서비스 구분을 위한 작업(구글: google, 네이버:naver)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        //Oauth2User를 리턴할때 정보를 넣는 부분

        String email;
        Map<String, Object> response = oAuth2User.getAttributes();
        //응답할때 Hashmap으로 사용할 것

        if(registrationId.equals("naver")){
            Map<String, Object> hash = (Map<String, Object>) response.get("response");

            email = (String) hash.get("email");
        }else if(registrationId.equals("google")){
            email = (String) response.get("email");
        }else{
            throw new OAuth2AuthenticationException("허용되지 않은 인증입니다.");
        }
        //네이버랑 구글이랑 이메일 받아오는 방식이 달라서 구분해준것. 이외의 것들은 일단 예외처리


        User user;
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()){//유저가 이미 존재하는 경우
            user = optionalUser.get();//정보가져옴
        }else{
            user = new User();
            user.setEmail(email); //아니면 받아온 정보로 만들어주자
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);
        }
        httpSession.setAttribute("user", user);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()))//권한 넣어줌
                , oAuth2User.getAttributes()
                , userNameAttributeName);
    }
}
