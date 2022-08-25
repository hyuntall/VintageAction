package hello.hellospring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "chatroomId")
    private ChatRoom chatroom;


    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    private MessageStatus status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime sendDate;

//    @OneToOne(mappedBy = "chatMessage")
//    private


    public enum MessageStatus{
        RECEIVED, DELIVERED
    }


}
