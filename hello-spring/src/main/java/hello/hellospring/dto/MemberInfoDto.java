package hello.hellospring.dto;

import hello.hellospring.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberInfoDto {

    private String id;
    private String name;


    @Builder
    public MemberInfoDto(Member member) {
        this.id = member.getMemberId();
        this.name = member.getMemberName();

    }
}
