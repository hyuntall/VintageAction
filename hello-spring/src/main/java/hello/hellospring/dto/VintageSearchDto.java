package hello.hellospring.dto;

import hello.hellospring.domain.VintageBoard;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

//중고상품 검색시 필요한 Dto
@Data
public class VintageSearchDto {
    @NotEmpty
    @NotBlank
    private String vintageTitle;

    public VintageBoard vintageList(){
        return VintageBoard.builder()
                .vintageTitle(vintageTitle)
                .build();
    }


}
