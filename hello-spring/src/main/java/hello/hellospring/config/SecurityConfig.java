package hello.hellospring.config;


import hello.hellospring.global.security.component.JsonUsernamePasswordAuthenticationFilter;
import hello.hellospring.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {  //spring security config코드

    private MemberService memberService;
    private final JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 서버에 요청되는 URL 중 인증이 필요하지 않은 URL에 대하여 스프링 시큐리티 인증을 무시하기 위한 설정
        web.ignoring().antMatchers("/css/**","/js/**","/img/**","/lib/**");
    }


    //spring securiy 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**").permitAll() // 모든 페이지 열람가능
                    .anyRequest().authenticated() // 나머지 페이지들 로그인해야 열람 가능
                    .and()
                .formLogin().disable()     // 로그인 설정
                    //== JSON 로그인 처리 ==//
                    .addFilterBefore(jsonUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)    // 세션 초기화
                    .and()
                .exceptionHandling();

    }


}
