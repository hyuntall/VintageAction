package hello.hellospring.dto;

import com.sun.istack.NotNull;
import hello.hellospring.domain.ChatMessage;
import hello.hellospring.repository.ChatRoomRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Component
public class ChatRequestDto {
    /**
     * 송신자 id
     */
    @NotNull
    private String senderId;

    /**
     * 수신자 id
     */
    @NotNull
    private String receiverId;

    /**
     * 채팅방 id
     */
    @NotNull
    private Long chatroomId;

    /**
     * 메시지 내용
     */
    @NotBlank
    private String content;

    public ChatRequestDto(String senderId, String receiverId, Long chatroomId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.chatroomId = chatroomId;
        this.content = content;
    }



    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .content(content)
                .build();
    }
}
