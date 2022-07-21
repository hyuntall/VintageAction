package hello.hellospring.domain;

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

    @OneToOne(mappedBy = "vintageItem", fetch = FetchType.LAZY)
    private VintageBoard vintageBoard;
}
