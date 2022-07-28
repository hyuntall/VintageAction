package hello.hellospring.global.exception;

//에러 일괄처리 위해 아직 구현중,,
public abstract class BaseException extends RuntimeException{
    public abstract BaseExceptionType getExceptionType();

}
