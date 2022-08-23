package hello.hellospring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor@Table(name="chatmessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String senderId;
    private String receiverId;
    private Long chatroomId;
    private Long itemId;
    private MessageStatus status;

//    @OneToOne(mappedBy = "chatMessage")
//    private


    public enum MessageStatus{
        RECEIVED, DELIVERED
    }


}
