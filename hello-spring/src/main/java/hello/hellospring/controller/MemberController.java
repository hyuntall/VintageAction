package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import hello.hellospring.domain.Member;
import org.springframework.ui.Model;
import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 버튼 클릭 시 회원가입 폼으로 이동
    @GetMapping(value = "/members/new")
    public String createForm() {

        return "members/createMemberForm";
    }

    // 회원가입 폼에서 정보 입력 후 등록 시 입력 정보를 MemberForm 객체에 등록
    // MemberForm 객체에 담긴 정보를 Member 객체에 등록 후 memberService.join에 Member 객체 전달
    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        //System.out.println(form.getName() + " / " + form.getId() + " / " + form.getPassword());
        member.setId(form.getId());
        member.setName(form.getName());
        member.setPassword(form.getPassword());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}