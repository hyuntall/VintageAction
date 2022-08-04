package hello.hellospring.service;

import hello.hellospring.domain.VintageBoard;
import hello.hellospring.dto.VintageBordForm;

import java.util.List;
import java.util.Optional;

public interface VintageService {
    //1. 중고거래 글 등록(게시글 내용, 아이템 정보를 받아온다.)
    VintageBoard save(VintageBordForm vintageForm, Long memberId);


    //2. 중고거래 글 수정

    //3. 중고거래 글 삭제

    //3. 중고거래 글 전부 다 조회
    List<VintageBoard> findAll();

    //4. 특정 중고거래 글 조회하기
    Optional<VintageBoard> findById(Long vintageBoardId);

    //5. 중고거래 글 특정 단어로 검색
}
