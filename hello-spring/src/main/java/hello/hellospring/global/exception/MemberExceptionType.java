package hello.hellospring.global.exception;

import org.springframework.http.HttpStatus;

//member로그인시 예외 코드 타입 지정 enum으로 작성  HttpStatus.BAD_REQUEST  400 오류코드로 일괄처리
public enum MemberExceptionType implements BaseExceptionType{
    //== 로그인 시 ==//
    WRONG_PASSWORD(601,HttpStatus.BAD_REQUEST, "비밀번호가 잘못되었습니다."),
    NOT_FOUND_MEMBER(602, HttpStatus.NOT_FOUND, "회원 정보가 없습니다.");
    //TODO : 로그인하지 않은 유저일 경우 발생시킬 예외 만들어야 함.

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    MemberExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

}
