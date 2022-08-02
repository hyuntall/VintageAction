package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.dto.MemberInfoDto;
import hello.hellospring.dto.MemberSignupDto;
import hello.hellospring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    
    //회원가입 페이지로 이동
    @GetMapping(value = "/api/members/new")
    public String createForm() {
        return "members/createdMemberForm";
    }

     

    /**
     * 회원 가입
     * @param memberSignupDto(id,name,password)
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/api/members/new")
    public ResponseEntity<String> create(@RequestBody MemberSignupDto memberSignupDto) throws Exception {
        System.out.println(memberSignupDto.getId());
        //Member member = memberSignupDto.toEntity();
        memberService.save(memberSignupDto);

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

//    @PostMapping(value = "/api/members/login")
//    public ResponseEntity<String> login(@RequestBody MemberInfoDto memberInfoupDto) throws Exception {
//        System.out.println(memberInfoupDto.getId());
//
//        return new ResponseEntity<String>("Success", HttpStatus.OK);
//    }


    /**
     * 회원 전부 조회
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/api/members")
    public ResponseEntity<?> memberList() throws Exception {
        List<Member> members = memberService.findAll();
        return ResponseEntity.ok(members);
    }

    /**
     * 내정보조회
     */
    @GetMapping("/api/member")
    public MemberInfoDto getMyInfo(
            HttpServletResponse response) throws Exception {

        MemberInfoDto info = memberService.getMyInfo();

        return info;
    }

    /**
     * 회원탈퇴
     */
    @DeleteMapping("/api/member/withdraw")
    public void withdraw() throws Exception {
        memberService.withdraw();
    }

}