
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
                .antMatchers("/auth").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/success").authenticated()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .logout().logoutUrl("/logout")// /logout 이용하며 logout 됨
                //TODO 로그아웃 시 이동하는 거 controller로 만들어야함.
                .logoutSuccessUrl("/login").permitAll()//로그아웃하면 이쪽으로 이동
                .and()
                .oauth2Login().loginPage("/login")
                //path variable 못씀!
                .defaultSuccessUrl("/success")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

    }
    //로그인 성공시 success 페이지로 이동하도록 수정
    //antMatchers는 리소스 권한 설정임! 기억!!
    //TODO antMathcers 줄여보기
}

