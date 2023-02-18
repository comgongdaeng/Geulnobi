package hello.geulenobi.controller;

import hello.geulenobi.domain.User;
import hello.geulenobi.repository.UserRepository;
import hello.geulenobi.service.EmailAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private HttpSession httpSession;
    private final UserRepository userRepository;
    private final EmailAuthService emailAuthService;

    @GetMapping("/signUp")
    public String signUpForm(@ModelAttribute("user") User user) {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String save(@Valid @ModelAttribute User user, BindingResult result, HttpSession session) throws Exception {
        if (result.hasErrors()) {
            return "signUp";
        }
        //TODO email 중복검사
        emailAuthService.sendEmailMessage(user.getEmail());
        session.setAttribute("user", user);
        return "auth";
    }

    @GetMapping("/auth")
    public String getAuth(@RequestParam String email, Model model) {
        model.addAttribute("email", email);
        return "auth";
    }

/*    @PostMapping("/auth")
    public ResponseEntity<String> authenticateUser(@RequestParam String code, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
        if (emailAuthService.validateCode(user.getEmail(), code)) {
            userRepository.save(user);
            session.removeAttribute("user");
            return new ResponseEntity<>("Authentication successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        }
    } */
    @PostMapping("/auth")
    public String authenticateUser(@RequestParam String code, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "signUp";
        }
        if (emailAuthService.validateCode(user.getEmail(), code)) {
            userRepository.save(user);
            session.removeAttribute("user");
            return "success";
        } else {
            return "auth";
        }
    }

}





