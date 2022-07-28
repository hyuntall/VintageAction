package hello.hellospring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private MessageType type;
    private String content;
    private String sender;
    private Long chatId;

//    @OneToOne(mappedBy = "chatMessage")
//    private

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }


}
