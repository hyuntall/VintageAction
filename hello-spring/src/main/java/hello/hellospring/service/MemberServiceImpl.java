package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //final 키워드가 붙은 것을 생성자 의존 관계 주입
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param member
     * @return
     */
    @Override
    public Member save(Member member) throws Exception{
        return memberRepository.save(member);
    }

    /**
     * 회원가입 시 id 중복체크(작성 중)
     * @param memberId
     * @return
     */
    @Override
    public boolean existsByMemberId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    /**
     * 회원no 로 조회하기
     * @param memberNo
     * @return
     * @throws Exception
     */
    @Override
    public Optional<Member> findOne(Long memberNo) throws Exception{
        Optional<Member> findMember = memberRepository.findById(memberNo);
        if (findMember.isEmpty())
            return Optional.empty();
        return findMember;
    }

    /**
     * 모든 회원 조회하기
     * @return
     * @throws Exception
     */
    @Override
    public List<Member> findAll() throws Exception{
        return memberRepository.findAll();
    }
}
