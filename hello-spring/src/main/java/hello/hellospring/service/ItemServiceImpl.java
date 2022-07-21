package hello.hellospring.service;


import hello.hellospring.domain.Item;
import hello.hellospring.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Override
    public Item save(Item item) {
        Item saveItem = itemRepository.save(item);
        return item;
    }
}
