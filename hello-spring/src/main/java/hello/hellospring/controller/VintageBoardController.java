package hello.hellospring.controller;


import hello.hellospring.domain.VintageBoard;
import hello.hellospring.dto.VintageBordForm;
import hello.hellospring.service.ItemService;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.VintageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class VintageBoardController {

    private final VintageService vintageService;
    private final MemberService memberService;
    private final ItemService itemService;


    // CREATE - 중고등록 로직 -> 등록이 성공적으로 완료되면 main 페이지로 이동한다.
    @PostMapping("/api/vintage/new")
    public ResponseEntity<?> createVintage(@Valid @RequestBody VintageBordForm vintageForm,
                                           BindingResult bindingResult,
                                           HttpServletRequest request) throws Exception {

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("validation error", HttpStatus.BAD_REQUEST);
        }

        //세션에 저장된 로그인 한 회원 정보 가져오기
        HttpSession session = request.getSession(false);
        Long memberId = (Long)session.getAttribute("memberNo");
        System.out.println(memberId);

        VintageBoard saveVintageBoard = vintageService.save(vintageForm, memberId);
        return new ResponseEntity<>(saveVintageBoard,HttpStatus.OK);
    }

    //READ - 중고상품목록 조회 -> 번호, 제목, 작성자Id 보여지기
    @GetMapping("/api/vintages")
    public ResponseEntity<?> vintageList(Model model) {
        List<VintageBoard> vintageBoards = vintageService.findAll();
        model.addAttribute("vintageBoards", vintageBoards);
        return new ResponseEntity<>(vintageBoards, HttpStatus.OK);
    }

    //중고상품 상세 조회
    @GetMapping("/api/vintages/{vintageBoardId}")
    public ResponseEntity<?> vintageDetail(@PathVariable("vintageBoardId") Long vintageBoardId, Model model) {
        Optional<VintageBoard> findVintageBoard = vintageService.findById(vintageBoardId);
        VintageBoard vintageBoard = findVintageBoard.get();
        return new ResponseEntity<>(vintageBoard, HttpStatus.OK);
    }

}
