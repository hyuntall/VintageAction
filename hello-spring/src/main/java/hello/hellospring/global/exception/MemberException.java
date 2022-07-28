package hello.hellospring.global.exception;

//Member에 대한 에외처리
public class MemberException extends BaseException{
    private BaseExceptionType exceptionType;

    public MemberException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
