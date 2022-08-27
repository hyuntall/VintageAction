package hello.hellospring.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="chatmessage")
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
    private LocalDateTime sendDateTime;




    public enum MessageStatus{
        RECEIVED, DELIVERED
    }


}
