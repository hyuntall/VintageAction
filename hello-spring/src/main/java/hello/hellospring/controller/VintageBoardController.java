package hello.hellospring.controller;

import hello.hellospring.domain.Item;
import hello.hellospring.domain.Member;
import hello.hellospring.domain.VintageBoard;
import hello.hellospring.service.ItemService;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.VintageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class VintageBoardController {

    private final VintageService vintageService;
    private final MemberService memberService;
    private final ItemService itemService;

    //중고상품 등록페이지로 이동
    @GetMapping("/vintage/new")
    public String createVintageForm(Model model) throws Exception {
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);
        return "/vintage/vintageRegister";
    }

    //중고등록 로직 -> 등록이 성공적으로 완료되면 main 페이지로 이동한다.
    @PostMapping("/vintage/new")
    public String createVintage(@RequestParam("vintageTitle") String title,
                                @RequestParam("itemName") String itemName,
                                @RequestParam("itemPrice") Integer itemPrice,
                                @RequestParam("vintageDetail") String detail,
                                @RequestParam("memberId") Long memberId) throws Exception {

        //선택한 회원정보 가져오기
        Optional<Member> findMember = memberService.findOne(memberId);
        Member member = findMember.get();


        //아이템 저장
        //중고거래 게시글을 등록하기 위해서는 아이템 저장이 선행되어야 한다.
        Item item = new Item();
        item.setItemName(itemName);
        item.setItemPrice(itemPrice);
        Item saveItem = itemService.save(item);

        //VintageBoard 파라미터 세팅
        VintageBoard vintageBoard = new VintageBoard();
        vintageBoard.setVintageTitle(title);
        vintageBoard.setVintageDetail(detail);

        //vintageBoard 아이템 세팅 - 연관관계 편의 메서드
        vintageBoard.setVintageItem(saveItem);

        //VintageBoard 작성자 세팅 - 연관관계 편의 메서드
        //vintageBoard.setMember(member);

        //중고거래 게시글 등록
       vintageService.save(vintageBoard);

        return "redirect:/";
    }

    //중고상품목록 조회 -> 번호, 제목, 작성자Id 보여지기
    @GetMapping("/vintages")
    public String vintageList(Model model) {
        List<VintageBoard> vintageBoards = vintageService.findAll();
        model.addAttribute("vintageBoards", vintageBoards);
        return "/vintage/vintageList";
    }

    //중고상품 상세 조회
    @GetMapping("/vintages/{vintageBoardId}")
    public String vintageDetail(@PathVariable("vintageBoardId") Long vintageBoardId, Model model) {
        Optional<VintageBoard> findVintageBoard = vintageService.findById(vintageBoardId);
        VintageBoard vintageBoard = findVintageBoard.get();
        model.addAttribute("vintageBoard",vintageBoard);
        return "/vintage/vintageDetail";
    }

}
