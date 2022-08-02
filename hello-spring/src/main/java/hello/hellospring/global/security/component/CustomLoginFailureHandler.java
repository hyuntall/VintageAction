package hello.hellospring.global.security.component;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//로그인 실패시 Handler
@Component
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
    private String defaultFailureUrl = "/api/dologin";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException ex)
            throws IOException, ServletException {

        // 실패로그를 남긴다
        // 실패이벤트를 발송한다
        System.out.println("exception type : "+ex.getClass().getName());
        System.out.println("exception message :" + ex.getMessage());

        String errorMessage;

        if(ex instanceof UsernameNotFoundException){
            errorMessage = "회원 계정이 존재하지 않습니다.";
        }else{
            errorMessage = "알 수 없는 이유로 로그인에 실패했습니다.";
        }
        defaultFailureUrl = "/api/dologin?error=true&exception="+errorMessage;

        // 실패시
        response.sendRedirect(defaultFailureUrl);
    }
}
