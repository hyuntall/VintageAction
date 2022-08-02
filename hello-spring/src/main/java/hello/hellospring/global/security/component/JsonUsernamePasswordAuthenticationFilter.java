package hello.hellospring.global.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    //spring security의 formLogin()을 사용하면 리액트에서 form-data 형식으로 값을 넘겨줘야 로그인 가능. 따라서 json으로 받을수 있게 security내의 코드를 받아와서 수정
    //security로 로그인을 하게 되면 UsernamePasswordAuthenticationFilter를 사용하게 된다.
    //코드 내부를 확인 하게 되면 username과 password를 getParameter로 가져오기 때문에 parameter형식이 아닌 json 데이터는 가져올 수 없다.
    //따라서 AbstractAuthenticationProcessingFilter나 UsernamePasswordAuthenticationFilter을 상속하여 json 데이터를 받을수 있도록 코드 수정.

    private final ObjectMapper objectMapper;
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    public static final String HTTP_METHOD = "POST";
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/api/dologin",
            HTTP_METHOD);  //로그인 api 수정 가능

    //로그인 요청이 들어오면  UsernamePasswordAuthenticationFilter가 작동(username과 password를 사용하여 인증을 처리하며, 기본 url은 /login이고, POST의 요청을 처리)

    @Autowired
    public JsonUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper,
                                                    AuthenticationManager authenticationManager,
                                                    CustomLogInSuccessHandler customLogInSuccessHandler,
                                                    CustomLoginFailureHandler customFailureHandler
    ) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);  //path지정
        this.objectMapper = objectMapper;
        setAuthenticationSuccessHandler(customLogInSuccessHandler);  //로그인 성공 핸들러
        setAuthenticationFailureHandler(customFailureHandler);  //로그인 실패 핸들러

    }


    //그 후 attemptAuthentication 메소드가 작동하여 인증을 처리
    //이때 username과 password로 UsernamePasswordAuthenticationToken을 만든 후, 이를 사용하여 AuthenticationManager의 authenticate()를 호출한다.
    //AuthenticationManager의 authenticate()에서는 UsernamePasswordAuthenticationToken의 정보를 이용하여 username과 password가 유효한지 검증한다.

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        log.info("JsonUsernamePasswordAuthenticationFilter 이 동작합니다");  //filter 동작시 log

        if (!request.getMethod().equals(HTTP_METHOD) || !request.getContentType().equals("application/json")) {
            log.error("POST 요청이 아니거나 JSON이 아닙니다"); //POST가 아니거나 JSON이 아닌 경우
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        LoginDto loginDto = objectMapper.readValue(StreamUtils.copyToString(request.getInputStream(), UTF_8), LoginDto.class);


        String username = loginDto.getUsername();  //로그인시 받은 아이디
        String password = loginDto.getPassword();  //로그인시 받은 비밀번호

        if(username == null || password == null){   //데이터가 비어 있을경우 오류발생
            throw new AuthenticationServiceException("DATA IS MISS");
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);   //Token으로 만든다
        setDetails(request, authRequest);  //LoginDto 클래스를 만들어서 request의 데이터를 파싱한다.
        return this.getAuthenticationManager().authenticate(authRequest); //getAuthenticationManager를 커스텀해 -> JsonAuthenticationManager를 사용
    }

    //HttpServletRequest = http프로토콜의 request정보를 서블릿에게 전달하기 위해 사용/헤더정보, 파라미터, 쿠키, URI, URL 등의 정보를 읽어 들이는 메소드 포함
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));  //Token에 로그인시 입력받은 정보를 set
    }

    @Data
    private static class LoginDto{  //LoginDto
        String username;
        String password;
    }

}
