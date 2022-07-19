package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<String, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 저장소에 멤버 정보 등록 ( 후에 DB에 등록하는 것으로 변경 예정 )
    @Override
    public Member save(Member member) {
        //member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // 중복 검사를 위해 저장소에 해당 계정이 있는지 확인
    @Override
    public Optional<Member> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
