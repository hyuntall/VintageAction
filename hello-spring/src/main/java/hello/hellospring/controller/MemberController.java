package hello.hellospring.controller;

import hello.hellospring.dto.MemberSignupDto;
import hello.hellospring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import hello.hellospring.domain.Member;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    /*
    //회원가입 페이지로 이동
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createdMemberForm";
    }

     */

    /**
     * 회원 가입
     * @param memberSignupDto(id,name,password)
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/members/new")
    public ResponseEntity<String> create(@RequestBody MemberSignupDto memberSignupDto) throws Exception {
        Member member = memberSignupDto.toEntity();
        memberService.save(member);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    /**
     * 회원가입 시 id 중복체크 (작성 중) -> 버튼 클릭 시 중복체크로 생각만 하는 중..
     * @param memberId
     * @return
     */
    @PostMapping ResponseEntity<?> checkId(@RequestParam("memberId") String memberId) throws Exception {
        if(memberService.existsByMemberId(memberId)){
            return ResponseEntity.ok("사용 가능한 아이디 입니다.");
        }else{
        }
        return null;
    }

    /**
     * 회원 전부 조회
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/members")
    public ResponseEntity<?> memberList() throws Exception {
        List<Member> members = memberService.findAll();
        return ResponseEntity.ok(members);
    }
}