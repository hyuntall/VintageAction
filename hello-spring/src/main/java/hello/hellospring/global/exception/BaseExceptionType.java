package hello.hellospring.global.exception;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {
    //get에러코드
    int getErrorCode();
    //Http 상태
    HttpStatus getHttpStatus();
    //에러메세지
    String getErrorMessage();

}
