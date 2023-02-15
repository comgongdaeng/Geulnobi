package hello.geulenobi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@Data
public class LoginForm {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
