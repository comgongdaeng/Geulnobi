package hello.geulenobi.controller;

import hello.geulenobi.domain.User;
import hello.geulenobi.domain.LoginForm;
import hello.geulenobi.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

/*    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/
    //private static final String authorizationRequestBaseUri = "oauth2/authorization/";
/*
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    private final ClientRegistrationRepository clientRegistrationRepository;

*/

    @RequestMapping(value="/login", method={RequestMethod.GET, RequestMethod.POST})
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {
        /*TODO 구글 로그인 버튼 눌렀을 때 -> 구글 로그인한 후에 다시 login으로 돌아옴 -> 인증 정보를 가져와서 가입을 안시킴.
            DB 내용이랑 비교해서 없으면 가입. 있으면 아래와 같이 가져오도록
            Caused by: org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: NULL not allowed for column "NAME"; SQL statement:
            니까 name이 비어있어서......... 그런거니까 구글 로그인 할때 이름도 가져오도록 설정해야함. +password도 nullable=false라서 어떻게 할지 고민해봐야됨!!!*/



        User loginUser = userService.login(loginForm.getEmail(), loginForm.getPassword());

        if (loginUser == null) {
            System.out.println("아이디 또는 비밀번호 불일치");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");

            //TODO 타임리프로 처리할 지, js로 처리할 지?
            //th:error를 사용해서 html에 나타낼 수는 있음.(현재 상태)

            return "login";
        }

        if (bindingResult.hasErrors()) {
            //
            return "login";
        }
        return "success";
    }

    @GetMapping
    public String logout(){
        return "signUp";
    }


}
