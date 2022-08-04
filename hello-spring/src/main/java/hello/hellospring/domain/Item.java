package hello.hellospring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false)
    private String itemName;

    //@Column(nullable = false)
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

    @Builder
    public Item(Long itemId, String itemName, String itemCategory, Integer itemPrice, String itemImage, Integer itemStartPrice, Integer itemCurPrice, VintageBoard vintageBoard) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
        this.itemStartPrice = itemStartPrice;
        this.itemCurPrice = itemCurPrice;
        this.vintageBoard = vintageBoard;
    }
}
