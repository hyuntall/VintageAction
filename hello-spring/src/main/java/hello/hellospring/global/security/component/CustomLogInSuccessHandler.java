package hello.hellospring.global.security.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//로그인 성공시 Handler
@Component
public class CustomLogInSuccessHandler implements AuthenticationSuccessHandler {
    private String defaultSuccessUrl = "/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        // 성공이후 로그를 님긴다
        System.out.println("로그인성공");
        // 성공이벤트를 발행한다.
        // 성공시 홈으로 이동
        response.sendRedirect(defaultSuccessUrl);
    }
}
