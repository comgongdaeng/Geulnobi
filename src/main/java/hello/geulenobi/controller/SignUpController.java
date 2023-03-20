package hello.geulenobi.controller;

import hello.geulenobi.domain.Role;
import hello.geulenobi.domain.User;
import hello.geulenobi.repository.GoogleUserRepository;
import hello.geulenobi.repository.UserRepository;
import hello.geulenobi.service.EmailAuthService;
import hello.geulenobi.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SignUpController {

    @Autowired
    private HttpSession httpSession; //이거 왜가져옴? 서블릿...써야하나? 세션 공부좀 ㅠ
    private final UserRepository userRepository;

    private final UserServiceImpl userService;
    //클래스를 impl로 써도 되나? 어떤게 나은지 찾아보기
    private final GoogleUserRepository googleUserRepository;
    private final EmailAuthService emailAuthService;

    @GetMapping("/signUp")
    public String signUpForm(@ModelAttribute("user") User user) {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String
    save(@Valid @ModelAttribute User user, BindingResult bindingResult, HttpSession session) throws Exception {
        if (bindingResult.hasErrors()) {
            return "signUp";
        }

        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            bindingResult.reject("signUpFail", "이미 존재하는 이메일 입니다.");
            return "signUp";
        }
        if(googleUserRepository.findByEmail(user.getEmail()).isPresent()){
            bindingResult.reject("signUpFail", "google 연동 로그인 이메일 입니다. google 로그인을 이용해주세요.");
            return "signUp";
        }

        emailAuthService.sendEmailMessage(user.getEmail());
        session.setAttribute("user", user);
        return "auth";
    }

    @GetMapping("/auth")
    public String getAuth(@RequestParam String email, Model model) {
        model.addAttribute("email", email);
        return "auth";
    }

    @PostMapping("/auth")
    public String authenticateUser(@RequestParam String code, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "signUp";
        }
        if (emailAuthService.validateCode(user.getEmail(), code)) {
            user.setRole(Role.USER);
            userService.register(user);
            //Todo 유저 서비스 가져와서 register로 등록하기. -->완료
            //userRepository.save(user);
            //여기 수정해야할것같은데..... 바로 넣는게 아니라 비밀번호 암호화하고 넣기.
            //암호화 저장 완료. 문제... 로그인 할때 오류뜸...!

            return "success";
        } else {
            return "auth";
        }
    }

}





