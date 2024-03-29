package hello.geulenobi.domain;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    //TODO 지장은 없지만 나중에 DTO를 inner class로 리팩터링 해보기(시간남으면...?)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String name;

    @Column(nullable = false)
    private Role role;


    //TODO 밑에 GETTER SETTER 따로 분리하는 것 같던데 찾아보고 나중에 리팩터링 진행.

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

    public void setRole(Role role) {
        this.role=role;
    }

    public Role getRole() {
        return role;
    }
}




