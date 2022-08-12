package hello.hellospring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(nullable = false, unique = true)
    private String memberId;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String memberPassword;

    private Long memberPoint;

    @JsonManagedReference // 양방향 관계에서 json 순화참조 에러 해결하기 위해서 넣었다.
    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<VintageBoard> vintageBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "auction_id")
    private List<AuctionBoard> auctionBoardList = new ArrayList<>();


    //DTO 클래스의 toEntity() 에서 사용하기 위해서 선언
    @Builder
    public Member(String memberId, String memberName, String memberPassword){
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
    }

    //회원 정보수정을 위한
    public void memberUpdate(String memberPassword){
        this.memberPassword = memberPassword;
    }

}
