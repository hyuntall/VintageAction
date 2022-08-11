package hello.hellospring.service;

import hello.hellospring.domain.ChatMessage;
import hello.hellospring.exception.ResourceNotFoundException;
import hello.hellospring.repository.ChatMessageRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{
    private ChatMessageRepository repository;
    private ChatRoomServiceImpl chatRoomServiceImpl;

    //수신한 채팅 저장
    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(ChatMessage.MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    //안 읽은 채팅 개수 표시
    public long countNewMessages(String senderId, String receiverId) {
        return repository.countBySenderIdAndReceiverIdAndStatus(
                senderId, receiverId, ChatMessage.MessageStatus.RECEIVED);
    }

    //안 읽은 채팅 가져오기
    public List<ChatMessage> findChatMessages(String senderId, String receiverId) {
//        String chatId = String.valueOf(chatRoomServiceImpl.getChatId(senderId, receiverId, false));
//
//        List<ChatMessage> messages =
//                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());
//
//        if(messages.size() > 0) {
//            updateStatuses(senderId, receiverId, ChatMessage.MessageStatus.DELIVERED);
//        }
//
//        return messages;

        List<ChatMessage> messages = null;
        return messages;
    }

    public ChatMessage findById(String id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(ChatMessage.MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }

    @Query
    public void updateStatuses(String senderId, String receiverId, ChatMessage.MessageStatus status) {
//        //UPDATE jpadb SET STATUS = status where senderId = senderId and receiverId = receiverId
//        Query query = new Query(
//                Criteria
//                        .where("senderId").is(senderId)
//                        .and("receiverId").is(receiverId));
//        Update update = Update.update("status", status);
//

    }
}
