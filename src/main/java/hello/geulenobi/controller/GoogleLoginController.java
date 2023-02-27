
package hello.geulenobi.controller;

import hello.geulenobi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class GoogleLoginController {
    private static final String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    @PostMapping("/login/{oauth2}")
    public String loginWithGoogle(@PathVariable String oauth2, HttpServletResponse httpServletResponse){
        return "redirect:/oauth2/authorization/" + oauth2;
    }



}

