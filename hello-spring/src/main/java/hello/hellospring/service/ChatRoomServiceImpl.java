package hello.hellospring.service;

import hello.hellospring.domain.ChatRoom;
import hello.hellospring.domain.Member;
import hello.hellospring.domain.VintageBoard;
import hello.hellospring.repository.ChatRoomRepository;
import hello.hellospring.repository.VintageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private ChatRoomRepository chatRoomRepository;
    private VintageRepository vintageRepository;

    public Optional<ChatRoom> findChatRoom(Long itemId, String senderId, boolean createIfNotExist) {

        return chatRoomRepository
                .findByItemIdAndBuyerId(itemId, senderId)
                .or(() -> { //없을 시 생성
                    if(!createIfNotExist) {  //채팅 생성은 구매자(sender)->판매자(receiver)만 가능
                        return  Optional.empty();
                    }
                    VintageBoard vintageBoard = vintageRepository.findByVintageItem(itemId);
                    Member seller = vintageBoard.getMember();
                    String sellerId = seller.getMemberId();
                    String buyerId = senderId;

                    ChatRoom newChatRoom = ChatRoom
                            .builder()
                            .itemId(itemId)
                            .buyerId(senderId)
                            .sellerId(sellerId)
                            .build();

                    chatRoomRepository.save(newChatRoom);

                    return Optional.of(newChatRoom);
                });
    }
}
