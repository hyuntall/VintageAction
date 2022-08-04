package hello.hellospring.dto;

import hello.hellospring.domain.Item;
import hello.hellospring.domain.VintageBoard;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VintageBordForm {

    @NotEmpty
    @NotBlank
    private String vintageTitle;
    @NotEmpty
    @NotBlank
    private String vintageDetail;

    @NotEmpty
    @NotBlank
    private String itemName;
    @NotNull //NotEmpty 는 String 타입에만 가능하다.
    private Integer itemPrice;


    public VintageBoard vintageFormtoEntity(){
        return VintageBoard.builder()
                .vintageTitle(vintageTitle)
                .vintageDetail(vintageDetail)
                .build();
    }

    public Item itemFormtoEntity(){
        return Item.builder()
                .itemName(itemName)
                .itemPrice(itemPrice)
                .build();
    }
}
