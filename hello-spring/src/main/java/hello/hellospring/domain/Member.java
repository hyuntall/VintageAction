package hello.hellospring.domain;

public class Member {

    private String id;
    private String nickName;
    private String password;
    private Long point;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String getNickName() {
        return nickName;
    }

    private void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private Long getPoint() {
        return point;
    }

    private void setPoint(Long point) {
        this.point = point;
    }

}
