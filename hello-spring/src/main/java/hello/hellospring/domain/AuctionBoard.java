package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auctionboard")
@Getter
@Setter
public class AuctionBoard extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionId;

    private String auctionTitle;
    private String auctionDetail;

    private Date auctionStartDay;
    private Date auctionEndDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item auctionItem;

    //==연관관계 메서드==// -> 양방향 관계에서 사용한다.
    //중고경매 게시물 등록할 때 사용자 설정
    public void setMember(Member member) {
        this.member = member;
        member.getAuctionBoardList().add(this);
    }

    //연관관계 메서드 - 양방향 관계에서 사용된다.
    //중고경매 게시물 등록할 때 item 관련 연관관계 편의 메서드
    public void setAuctionItem(Item auctionItem) {
        this.auctionItem = auctionItem;
        auctionItem.setAuctionBoard(this);
    }


}
