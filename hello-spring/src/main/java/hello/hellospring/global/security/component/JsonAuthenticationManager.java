package hello.hellospring.global.security.component;


import hello.hellospring.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonAuthenticationManager implements AuthenticationManager, AuthenticationProvider {
    //AuthenticationFilter에 의해 AuthenticationManager가 동작한다. AuthenticationProvider가 실질적 동작
    //AuthenticationProvider에서 UsernamePasswordAuthenticationToken의 정보를 이용하여 username과 password가 유효한지 검증을 실시한다.
    //UserDetailsService를 주입받아서, 검증을 시도
    //상속받은 MemberService에서 username을 통해서 DB에서 회원을 찾아오고, 비밀번호를 검증
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;  //패스워드 암호화

    //AuthenticationProvider에서 Authentication실행 한다.  username과 password가 유효한지 검증.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        log.info("JsonAuthenticationManager 이 동작합니다");
        String password = (String) authentication.getCredentials();  //입력된 암호화값

        //username을 통해 userDetails를 구현. userDetails = security속 Member역할
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());
        String passwordFromDb = userDetails.getPassword();  //db에 있는 암호화된 값을 변수에

        if (!passwordEncoder.matches(password, passwordFromDb)) {   //패스워드 비교
            throw new BadCredentialsException("비밀번호가 틀립니다.");
        }


        //비밀번호 일치시 Token을 만든다.
        return new UsernamePasswordAuthenticationToken(userDetails
                , userDetails.getPassword()
                , userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
