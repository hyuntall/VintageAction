package hello.hellospring.service;

import hello.hellospring.domain.Item;

public interface ItemService {
    //1. 아이템 저장
    Item save(Item item);
}
