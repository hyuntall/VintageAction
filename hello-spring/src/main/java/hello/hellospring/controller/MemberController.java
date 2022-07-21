package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import hello.hellospring.domain.Member;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    //회원가입 페이지로 이동
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createdMemberForm";
    }

    //회원가입 로직
    @PostMapping(value = "/members/new")
    public String create(String id, String name, String password) {
        Member member = new Member();
        member.setMemberId(id);
        member.setMemberName(name);
        member.setMemberPassword(password);

        Member createdMember = memberService.save(member);
        System.out.println(createdMember.getMemberId()+" "+createdMember.getMemberName());
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);
        return "/members/memberList";
    }
}