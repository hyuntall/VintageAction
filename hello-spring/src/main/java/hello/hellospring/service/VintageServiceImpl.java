package hello.hellospring.service;


import hello.hellospring.domain.Item;
import hello.hellospring.domain.Member;
import hello.hellospring.domain.VintageBoard;
import hello.hellospring.dto.VintageBordForm;
import hello.hellospring.repository.ItemRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.VintageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VintageServiceImpl implements VintageService{

    private final VintageRepository vintageRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Override
    public VintageBoard save(VintageBordForm vintageForm,
                             Long memberId) {
        //DTO -> Entity
        Item item = vintageForm.itemFormtoEntity();
        VintageBoard vintageBoard = vintageForm.vintageFormtoEntity();

        //아이템 등록 후, vintageBoard에 item 세팅
        itemRepository.save(item);
        vintageBoard.setVintageItem(item);

        //회원 조회 후, vintageBoard에 회원 세팅
        Optional<Member> member = memberRepository.findById(memberId);
        vintageBoard.setMember(member.get());

        VintageBoard saveVintageBoard = vintageRepository.save(vintageBoard);
        return saveVintageBoard;
    }

    @Override
    public List<VintageBoard> findAll() {
        List<VintageBoard> vintageBoards = vintageRepository.findAll();
        return vintageBoards;
    }


    @Override
    public Optional<VintageBoard> findById(Long vintageBoardId) {
        Optional<VintageBoard> findVintageBoard = vintageRepository.findById(vintageBoardId);
        if(findVintageBoard.isEmpty())
            return Optional.empty();
        return findVintageBoard;
    }
}
