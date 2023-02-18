/*
package hello.geulenobi.controller;

import hello.geulenobi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class GoogleLoginController {
    private static final String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    // private final UserRepository;

    @GetMapping("/login/{oauth2}")
    public String loginGoogle(@PathVariable String oauth2, HttpServletResponse httpServletResponse){
        return "redirect:/oauth2/authorization/" + oauth2;
    }

    //auth 하고나면 다시 원래 페이지로 redirect 되나? 아니면 직접 설정?
    // --> Oauth마다 다른데 securityConfig에서 설정해줄 수 있다.
    // defaultSuccessURL() 이용해서..

}
*/
