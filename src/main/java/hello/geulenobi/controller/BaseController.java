package hello.geulenobi.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor

public class BaseController {

    @GetMapping("/")
    public String index() {
        return "home";
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
    public String getLoginPage(Model model) throws Exception {
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

        return "login"; //인증정보 URL을 가져서 Login으로
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
