package hello.hellospring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private MessageType type;
    private String content;
    private String senderId;
    private String receiverId;
    private String chatId;
    private MessageStatus status;

//    @OneToOne(mappedBy = "chatMessage")
//    private

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public enum MessageStatus{
        RECEIVED, DELIVERED
    }


}
