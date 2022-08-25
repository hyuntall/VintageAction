package hello.hellospring.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
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
    private Long roomId;

    /**
     * 메시지 내용
     */
    @NotBlank
    private String content;

    public ChatRequestDto(String senderId, String receiverId, Long roomId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.roomId = roomId;
        this.content = content;
    }
}
