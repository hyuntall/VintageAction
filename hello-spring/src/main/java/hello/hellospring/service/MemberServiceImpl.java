package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.domain.Role;
import hello.hellospring.dto.MemberInfoDto;
import hello.hellospring.dto.MemberSignupDto;
import hello.hellospring.global.SecurityUtil;
import hello.hellospring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //final 키워드가 붙은 것을 생성자 의존 관계 주입
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param
     * @return
     */
    @Override
    public Member save(MemberSignupDto memberDto) throws Exception{
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity());
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new UsernameNotFoundException("아이디가 없습니다"));

        return User.builder().username(member.getMemberId())
                .password(member.getMemberPassword())
                .roles(member.getRole().name())
                .build();

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

    /**
     * 내정보 가져오기
     */
    @Override
    public MemberInfoDto getMyInfo() throws Exception {
        Member findMember = memberRepository.findByMemberId(SecurityUtil.getLoginUsername()).orElseThrow(() -> new Exception("회원이 없습니다"));
        return new MemberInfoDto(findMember);
    }

}
