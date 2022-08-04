package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.dto.MemberInfoDto;
import hello.hellospring.dto.MemberSignupDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public interface MemberService{

    //회원가입
    Member save(MemberSignupDto memberDto) throws Exception;

    //회원가입 시 아이디 중복 체크
    boolean existsByMemberId(String memberId) throws Exception;

    //특정 회원 조회
    Optional<Member> findOne(Long memberNo) throws Exception;

    //회원 전체 조회
    List<Member> findAll() throws Exception;

    //내 정보 조회
    MemberInfoDto getMyInfo() throws Exception;

    //회원 정보 수정(비밀번호 변경)

    //회원 탈퇴
    void withdraw(Member deleteMember) throws Exception;
}
