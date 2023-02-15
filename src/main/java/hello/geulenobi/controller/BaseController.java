package hello.geulenobi.controller;

import hello.geulenobi.domain.User;
import hello.geulenobi.domain.UserRepository;
import hello.geulenobi.dto.LoginForm;
import hello.geulenobi.session.SessionConst;
import hello.geulenobi.session.SessionManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Controller
//@AllArgsConstructor
@RequiredArgsConstructor

public class BaseController {
//리팩터링 필요....


    private final UserRepository userRepository;
    private final SessionManager sessionManager;

    //@GetMapping("/")
    public String index() {
        return "home";
    }
    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {

        //세션에 회원 데이터가 없으면 home
        if (loginUser == null) {
            return "home";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginUser);
        return "success";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    private static final String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    private final ClientRegistrationRepository clientRegistrationRepository;


    @GetMapping("/login/{oauth2}")
    public String loginGoogle(@PathVariable String oauth2, HttpServletResponse httpServletResponse){
        return "redirect:/oauth2/authorization/" + oauth2;
    }

    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute("loginForm") LoginForm form, Model model ) throws Exception {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }
        assert clientRegistrations != null;
        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        System.out.println(model.getAttribute("urls"));

        return "login"; //인증정보 URL을 login에서 쓸 것.
    }


    //@GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/success")
    public String loginSuccess(){
        return "success";
    }



    @RequestMapping("/accessDenied")
    public String accessDenied() {return "accessDenied";}




}
