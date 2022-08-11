package hello.hellospring.service;

import hello.hellospring.domain.ChatRoom;
import hello.hellospring.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatId(String senderId, String receiverId, boolean createIfNotExist) {

        return chatRoomRepository
                .findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() -> { //없을 시 생성(2명 사용자 각각)
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                    String chatId =
                            String.format("%s_%s", senderId, receiverId); //보낸사람_받는사람 형태의 chatId

                    ChatRoom senderReceiver = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .receiverId(receiverId)
                            .build();

                    ChatRoom receiverSender = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(receiverId)
                            .receiverId(senderId)
                            .build();
                    chatRoomRepository.save(senderReceiver);
                    chatRoomRepository.save(receiverSender);

                    return Optional.of(chatId);
                });
    }
}
