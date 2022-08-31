package hello.hellospring.service;

import hello.hellospring.domain.ChatMessage;
import hello.hellospring.dto.ChatRequestDto;

import java.util.List;

public interface ChatMessageService {
    //수신한 채팅 저장
    public ChatMessage save(ChatRequestDto chatRequestDto);

    //안 읽은 채팅 개수 표시
    public long countNewMessages(String senderId, String receiverId);

    //안 읽은 채팅 가져오기
    public List<ChatMessage> findChatMessages(String senderId, String receiverId);

    //채팅 status update
    public void updateStatuses(String senderId, String receiverId, ChatMessage.MessageStatus status);

    ChatMessage addChatroomId(Long id, Long chatroomId);
}
