package hello.geulenobi.config;


import hello.geulenobi.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/login/google").permitAll()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/google.png").denyAll()
                .antMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .oauth2Login().loginPage("/login")
                .defaultSuccessUrl("/success")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

    }
    //로그인 성공시 success 페이지로 이동하도록 수정
    //로그인 성공했는데 success 페이지 뜨지 않는 오류 발생... TODO
    //antMatchers는 리소스 권한 설정임! 기억!!
}
