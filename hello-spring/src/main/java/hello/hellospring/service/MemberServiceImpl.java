package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.dto.MemberInfoDto;
import hello.hellospring.dto.MemberSignupDto;

import hello.hellospring.dto.MemberUpdateDto;
import hello.hellospring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //final 키워드가 붙은 것을 생성자 의존 관계 주입
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member save(MemberSignupDto memberSignupDto) throws Exception {
        Member member = memberSignupDto.toEntity();
        member.setMemberPoint(10000L);
        Member saveMember = memberRepository.save(member);
        return saveMember;
    }

    @Override
    public boolean existsByMemberId(String memberId) throws Exception {
        return false;
    }

    @Override
    public Optional<Member> findOne(Long memberNo) throws Exception {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() throws Exception {
        return null;
    }

    @Override
    public MemberInfoDto getMyInfo() throws Exception {
        return null;
    }

    @Override
    @Transactional  //JPA 영속성을 위해 어노테이션 추가 _ 트랜젝션 종료후 JPARepository에 변화가 반영됨
    public void memberUpdate(String id, MemberUpdateDto memberUpdateDto){
        Member member = memberRepository.findByMemberId(id).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 없습니다."));

        member.memberUpdate(memberUpdateDto.getPassword());

    }

    @Override
    public void withdraw(Member deleteMember) throws Exception {

        memberRepository.delete(deleteMember);
    }


//    @Override
//    public MemberInfoDto getMyInfo() throws Exception {
//        Member findMember = memberRepository.findByMemberId(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 없습니다"));
//        return new MemberInfoDto(findMember);
//    }


}
