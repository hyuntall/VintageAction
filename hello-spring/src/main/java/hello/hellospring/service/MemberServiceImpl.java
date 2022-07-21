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

    @Override
    public Member save(Member member) {
        Member createdMember = memberRepository.save(member);
        return member;
    }

    @Override
    public Optional<Member> findOne(Long memberNo) {
        Optional<Member> findMember = memberRepository.findById(memberNo);
        if (findMember.isEmpty())
            return Optional.empty();
        return findMember;
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = memberRepository.findAll();
        return members;
    }
}
