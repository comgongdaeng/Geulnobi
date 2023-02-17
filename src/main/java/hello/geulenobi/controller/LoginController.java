package hello.geulenobi.controller;

import hello.geulenobi.domain.User;
import hello.geulenobi.domain.LoginForm;
import hello.geulenobi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

/*    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/


    @GetMapping("/login")
    public String loginPage(@ModelAttribute("loginForm") LoginForm loginForm){
        return "login";
    }
    //입력폼 페이지 조회시, 템플릿에서 th:object="${loginForm}에서 loginForm에 대한 정보를 전달받지 못해 발생했던 문제였음.


    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // handle validation errors
            return "login";
        }

        User loginUser = userService.login(loginForm.getEmail(), loginForm.getPassword());
        if (loginUser == null) {
            System.out.println("아이디 또는 비밀번호 불일치");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");

            //TODO 타임리프로 처리할 지, js로 처리할 지?
            //th:error를 사용해서 html에 나타낼 수는 있음.

            return "login";
        }

        // add any other necessary objects to the model


        return "success";
    }


}
