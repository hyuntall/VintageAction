package hello.hellospring.repository;

import hello.hellospring.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    long countBySenderIdAndReceiverIdAndStatus(
            String senderId, String receiverId, ChatMessage.MessageStatus status);

    List<ChatMessage> findByChatroomId(Long id);

}
