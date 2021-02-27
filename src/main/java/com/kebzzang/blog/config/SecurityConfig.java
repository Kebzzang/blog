package com.kebzzang.blog.config;

import com.kebzzang.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//bean등록 : t스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration //빈등록(IoC관리)
@EnableWebSecurity //시큐리티에 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다!!
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean //DI
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private PrincipalDetailService principalDetailService;


    @Bean //IoC  함수가 리턴하는 값을 스프링이 관리하게 됨.
    public BCryptPasswordEncoder encodePWD(){
        //String encPassword=new BCryptPasswordEncoder().encode("1234");
        return new BCryptPasswordEncoder();
    }

    //시큐리티가 대신 로그인해줄 때 password를 가로채기 하는데
    //해당 패스워드가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    //같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음 password 비교
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http
                .csrf().disable() //csrf 토큰 비활성화 ( 테스트 시 걸어두는 게 좋음 )
                .authorizeRequests()
                .antMatchers("/auth/**", "/js/**", "/css/**", "/image/**", "/", "/dummy/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        .and()
            .formLogin()
            .loginPage("/auth/loginForm")
            .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해준다
            .defaultSuccessUrl("/"); //로그인에 성공하면 이곳으로 이동해라 (정상적으로 요청 완료)

    }
}
