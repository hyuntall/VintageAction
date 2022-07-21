package hello.hellospring.repository;


import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //Optional<Member> findByMemberIdIs(String memberId); //사용자 id로 검색
}
