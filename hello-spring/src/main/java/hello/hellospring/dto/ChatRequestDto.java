package hello.hellospring.dto;

import com.sun.istack.NotNull;
import hello.hellospring.domain.ChatMessage;
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
/*TODO: 순환참조 해결, chatroomId chatroom으로 정상매핑*/
//    private ChatRoomRepository chatRoomRepository;
//    private ChatRoom chatRoom =  chatRoomRepository.findById(chatroomId);

    public ChatMessage toEntity() {
        return ChatMessage.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .content(content)
                .chatroomId(chatroomId)
                .build();
    }
}
