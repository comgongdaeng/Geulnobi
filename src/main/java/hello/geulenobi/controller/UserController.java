package hello.geulenobi.controller;

import hello.geulenobi.domain.User;
import hello.geulenobi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/signUp")
    public String addForm(@ModelAttribute("user") User user) {
        return "signUp";
    }
    @PostMapping("/signUp")
    public String save(@Valid @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "signUp";
        }
        userRepository.save(user);
        return "redirect:/";
    }
/*    @GetMapping("/signUp")
    public String registerPage(){
        return "signUp";
    }

    @PostMapping("/signUp")
    public String registerUser(@Valid @RequestBody User user) {

        User existingUser = userService.register(user);
        if (existingUser != null) {
            return "singUp";
        }
        return "redirect:/";
    }*/
}
