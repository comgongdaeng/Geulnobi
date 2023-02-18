package hello.geulenobi.domain;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    //TODO 이메일 인증번호 받아서 처리할 Table 새로 만들기. fk 사용해서 처리하는 걸로?
    //TODO 지장은 없지만 나중에 DTO를 inner class로 리팩터링 해보기(시간남으면...?)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}




