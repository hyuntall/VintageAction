package hello.hellospring.service;


import hello.hellospring.domain.VintageBoard;
import hello.hellospring.repository.ItemRepository;
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

    @Override
    public VintageBoard save(VintageBoard vintageBoard) {
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
