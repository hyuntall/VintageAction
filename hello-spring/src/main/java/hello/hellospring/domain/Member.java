package hello.hellospring.domain;

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

    @OneToMany(mappedBy = "vintageId")
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

    //패스워드 암호화
    public void encryptPassword(PasswordEncoder passwordEncoder) {
        memberPassword = passwordEncoder.encode(memberPassword);
    }


}
