package hello.hellospring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false)
    private String itemName;

    private String itemCategory;

    @Column(nullable = false)
    private Integer itemPrice;

    private String itemImage;
    private Integer itemStartPrice;
    private Integer itemCurPrice;

    @JsonManagedReference
    @OneToOne(mappedBy = "vintageItem", fetch = FetchType.LAZY)
    private VintageBoard vintageBoard;

    @OneToOne(mappedBy = "auctionItem", fetch = FetchType.LAZY)
    private AuctionBoard auctionBoard;


    @JsonManagedReference // 양방향 관계에서 json 순화참조 에러 해결하기 위해서 넣었다.
    @OneToMany(mappedBy = "uploadItem", orphanRemoval = true) //mappedBy의 value는 연관관계의 주인 컬럼명을 적는다.
    private List<UploadFile> uploadFiles = new ArrayList<>();


    @Builder
    public Item(String itemName, Integer itemPrice, String itemCategory) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
    }
}
