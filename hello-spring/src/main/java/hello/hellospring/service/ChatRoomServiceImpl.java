package hello.hellospring.service;

import hello.hellospring.domain.ChatRoom;
import hello.hellospring.domain.Item;
import hello.hellospring.domain.Member;
import hello.hellospring.domain.VintageBoard;
import hello.hellospring.repository.ChatRoomRepository;
import hello.hellospring.repository.ItemRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.VintageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private ChatRoomRepository chatRoomRepository;
    private VintageRepository vintageRepository;
    private ItemRepository itemRepository;
    private MemberRepository memberRepository;

    public Optional<ChatRoom> findChatRoom(Long itemId, String senderId, boolean createIfNotExist) {

        return chatRoomRepository
                .findByItemAndBuyer(itemId, senderId)
                .or(() -> { //없을 시 생성
                    if(!createIfNotExist) {  //채팅 생성은 구매자(sender)->판매자(receiver)만 가능
                        return  Optional.empty();
                    }
                    VintageBoard vintageBoard = vintageRepository.findByVintageItem(itemId);
                    Item item = itemRepository.findByItemId(itemId);
                    Member buyer = memberRepository.findByMemberId(senderId).orElseThrow(() -> new IllegalArgumentException("보내는 이가 등록되지 않은 사용자입니다."));;
                    Member seller = vintageBoard.getMember();

                    ChatRoom newChatRoom = ChatRoom
                            .builder()
                            .item(item)
                            .buyer(buyer)
                            .seller(seller)
                            .build();

                    chatRoomRepository.save(newChatRoom);

                    return Optional.of(newChatRoom);
                });
    }
}
