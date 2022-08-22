package hello.hellospring.controller;


import hello.hellospring.domain.Item;
import hello.hellospring.domain.UploadFile;
import hello.hellospring.domain.VintageBoard;
import hello.hellospring.dto.VintageBordForm;
import hello.hellospring.dto.VintageSearchDto;
import hello.hellospring.service.ItemService;
import hello.hellospring.service.MemberService;
import hello.hellospring.service.VintageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
<<<<<<< HEAD
import java.io.IOException;
import java.io.InputStream;
=======
>>>>>>> 527e39542fa595c8ada56163a726a9343288e9bb
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class VintageBoardController {

    private final VintageService vintageService;
    private final ItemService itemService;


    // CREATE - 중고등록 로직 -> 등록이 성공적으로 완료되면 main 페이지로 이동한다.
    @PostMapping("/api/vintage/new")
    public ResponseEntity<?> createVintage(@Valid @ModelAttribute VintageBordForm vintageForm,
                                           BindingResult bindingResult,
                                           HttpServletRequest request) throws Exception {

        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, Object> result = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                result.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        //세션에 저장된 로그인 한 회원 정보 가져오기
        HttpSession session = request.getSession();
        Long memberId = (Long)session.getAttribute("memberNo");

        VintageBoard saveVintageBoard = vintageService.save(vintageForm, memberId);
        return new ResponseEntity<>(saveVintageBoard,HttpStatus.OK);
    }

    //READ - 중고상품목록 조회 -> 번호, 제목, 작성자Id 보여지기
    @GetMapping("/api/vintages")
    public ResponseEntity<?> vintageList( @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<VintageBoard> vintageBoards = vintageService.findAll(page);

        if(page >= vintageBoards.getTotalPages()){
            return new ResponseEntity<>("중고상품이 더 이상 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        //vintageBoard 의 필요한 정보만 배열로 만들기
        Stream<VintageBoard> vintageBoardStream = vintageBoards.get();
        List<VintageSearchDto> searchResult = new ArrayList<>();
        for (VintageBoard vintageBoard : vintageBoards) {
            searchResult.add(new VintageSearchDto(
                    vintageBoard.getVintageId(),
                    vintageBoard.getVintageTitle(),
                    vintageBoard.getVintageItem().getUploadFiles()
            ));
        }

        //결과값 세팅
        Map<String, Object> result = new HashMap<>();
        result.put("vintageBoard", searchResult); // vintageSearchDto 들
        result.put("totalPage",vintageBoards.getTotalPages()); // 총 페이지 수

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //READ - 중고상품 상세 조회 -> 제목 / 아이템명, 아이템 가격, 아이템 이미지 / 설명
    @GetMapping("/api/vintage/{vintageBoardId}")
    public ResponseEntity<?> vintageDetail(@PathVariable("vintageBoardId") Long vintageBoardId) throws IOException {
        Optional<VintageBoard> findVintageBoard = vintageService.findById(vintageBoardId);
        VintageBoard vintageBoard = findVintageBoard.get();


        Map<String, Object> result = new HashMap<>();
        result.put("title", vintageBoard.getVintageTitle());
        result.put("detail", vintageBoard.getVintageDetail());
        result.put("itemName", vintageBoard.getVintageItem().getItemName());
        result.put("itemPrice", vintageBoard.getVintageItem().getItemPrice());
        result.put("itemCategory", vintageBoard.getVintageItem().getItemCategory());
        result.put("itemImages", vintageBoard.getVintageItem().getUploadFiles());
        result.put("memberId", vintageBoard.getMember().getMemberId());
        result.put("createdTime", vintageBoard.getCreatedTime());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //UPDATE - 중고상품 업데이트
    @PostMapping("/api/vintage/{vintageBoardId}/edit")
    public ResponseEntity<?> updateVintage(@ModelAttribute VintageBordForm vintageForm, @PathVariable("vintageBoardId") Long vintageBoardId, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        Long memberNo = (Long)session.getAttribute("memberNo");
        VintageBoard updateVintageBoard = vintageService.update(vintageBoardId, vintageForm, memberNo);

        return new ResponseEntity<>(updateVintageBoard, HttpStatus.OK);

    }

    //DELETE - 중고상품 삭제
    @PostMapping("/api/vintage/{vintageBoardId}/delete")
    public ResponseEntity<?> deleteVintage(@PathVariable("vintageBoardId") Long vintageBoardId,
                                           HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Long memberNo = (Long)session.getAttribute("memberNo");

        vintageService.delete(vintageBoardId, memberNo);

        return new ResponseEntity<>("게시물 삭제 완료", HttpStatus.OK);
    }

    //중고상품 검색
    @GetMapping("/api/vintages/search/{vintageTitle}") //page:default 페이지, size:한 페이지 게시글 수, sort:정렬기준컬럼, direction:정렬순서
    public ResponseEntity<?> search(@PathVariable("vintageTitle") String vintageTitle,
                                    @PageableDefault(page =0, size=10, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable){
        System.out.println(vintageTitle);
        Page<VintageBoard> vintageBoardList = vintageService.search(vintageTitle, pageable);

//        Stream<VintageBoard> vintageBoardStream = vintageBoardList.get();
//        List<VintageSearchDto> s = new ArrayList<>();
//        for (VintageBoard vintageBoard : vintageBoardList) {
//            VintageSearchDto tmp = new VintageSearchDto();
//            tmp.setVintageTitle(vintageBoard.getVintageId());
//            vintageBoard.getVintageTitle();
//            vintageBoard.getVintageItem().getUploadFiles();
//
//            s.add(s)
//        }
//
//        Map<String, Object> result = new HashMap<>();
//        result.put()
//        pageable.previousOrFirst().getPageNumber(); //이전 페이지
//        pageable.next().getPageNumber(); //다음 페이지

        return new ResponseEntity<>(vintageBoardList,HttpStatus.OK);

    }

    @GetMapping("/api/vintages/category/{itemCategory}")
    public ResponseEntity<?> category(@PathVariable("itemCategory") String itemCategory,
                                      @PageableDefault(page=0, size = 10) Pageable pageable, Model model){
        List<Long> itemList = itemService.findByCategory(itemCategory);
        Page<VintageBoard> vintageBoardList = vintageService.findByItemId(itemList, pageable);
        return new ResponseEntity<>(vintageBoardList, HttpStatus.OK);

    }

}
