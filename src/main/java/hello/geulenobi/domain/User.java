package hello.geulenobi.domain;


import hello.geulenobi.dto.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

//Data어노테이션만으로 동작하는지 확인 필요 TODO
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class User implements Serializable {
    //User table
    //나중에 dto 분할 필요... 리팩터링 해야할듯

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //임시 pk
    @NotEmpty
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    //보안 업그레이드 필요

    //소셜로그인으로 자동 인서트 될것.(로그인성공시)

    private int mail_auth;

    private String mail_key;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;




}
