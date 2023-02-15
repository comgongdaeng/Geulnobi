package hello.geulenobi.domain;


import hello.geulenobi.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {
    //User table
    //나중에 dto 분할 필요... 리팩터링 해야할듯

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private  String email;
    //소셜로그인으로 자동 인서트 될것.(로그인성공시)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;




}
