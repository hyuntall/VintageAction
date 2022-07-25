package hello.hellospring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String memberPassword;

    private Long memberPoint;

    @OneToMany(mappedBy = "vintageId")
    private List<VintageBoard> vintageBoardList = new ArrayList<>();

    //DTO 클래스의 toEntity() 에서 사용하기 위해서 선언
    @Builder
    public Member(String memberId, String memberName, String memberPassword){
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
    }
}
