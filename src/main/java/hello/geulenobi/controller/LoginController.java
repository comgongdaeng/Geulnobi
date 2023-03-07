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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class LoginController {

    @Autowired
    private UserServiceImpl userService;


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

    @GetMapping("/user_only")
    public String user_only(){return "user_only"; }

}
