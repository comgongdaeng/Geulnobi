package hello.geulenobi.controller;

import hello.geulenobi.domain.User;
import hello.geulenobi.domain.LoginForm;
import hello.geulenobi.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private HttpSession httpSession;


/*    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/
    //private static final String authorizationRequestBaseUri = "oauth2/authorization/";
/*
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    private final ClientRegistrationRepository clientRegistrationRepository;

*/

    //로그인 get과 post 분리하였음.
    @GetMapping("/login")
    public String loginPage(@ModelAttribute("loginForm") LoginForm loginForm, Model model,HttpSession session){

        if(session.getAttribute("user")==null){
            return "login";
        }

        return "success";

    }
    @PostMapping("/login")
    public String loginPage(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model, HttpSession session){
        //1.세션에 정보가 있으면 그냥 바로 success로 보내고
        //2.세션에 정보가 없으면 login창 보여주고 하는게 가능한가? 어떻게 해야하지?
        //세션의 user가 null이 아닌지 if문으로 검사 ->null아니면 바로 success로 리다이렉트/null이면 if문 건너뛰고 login로직.
        //getMapping에서 해야되겠다.
        //login


        User loginUser = userService.login(loginForm.getEmail(), loginForm.getPassword());
        //
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

        session.setAttribute("user", loginUser);

        return "success";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }

    @GetMapping("/success")
    public String loginSuccess(){
        return "success";
    }

}
