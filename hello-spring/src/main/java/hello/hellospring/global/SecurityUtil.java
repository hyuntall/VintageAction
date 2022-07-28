package hello.hellospring.global;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {
    public static String getLoginUsername(){


        // TODO: 로그인한 유저가 있으면 반환, 없으면 예외 발생
        // SecurityContextHolder에서 username을 꺼내오는 코드

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        return String.valueOf(user.getUsername());   //postman에 String.valurOf()해야 보임
        return user.getUsername();
    }
}
