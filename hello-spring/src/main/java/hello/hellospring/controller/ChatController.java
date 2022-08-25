package hello.hellospring.controller;


import hello.hellospring.domain.ChatMessage;
import hello.hellospring.domain.ChatNotification;
import hello.hellospring.domain.ChatRoom;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.ChatMessageService;
import hello.hellospring.service.ChatRoomService;
import hello.hellospring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MemberService memberService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private ChatRoomService chatRoomService;
    private ChatMessageService chatMessageService;

    @GetMapping("/chat/new/{receiverId}/{chatroomId}/{itemId}")
    public String createChatForm(@PathVariable Map<String, String> pathVarsMap, Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        String senderId = (String) session.getAttribute("memberId");
        model.addAttribute("senderId",senderId);

        String receiverId = pathVarsMap.get("receiverId");
        String chatroomId = pathVarsMap.get("chatroomId");
        String itemId = pathVarsMap.get("itemId");
        model.addAttribute("receiverId", receiverId);
        model.addAttribute("chatroomId", chatroomId);
        model.addAttribute("itemId", itemId);

        return "/chat/chatRoom";
    }



    @MessageMapping("/chat")  //여기로 전송되면 메서드 호출
    public void processMessage(@Payload ChatMessage chatMessage) {
        setChatroomId(chatMessage);

        ChatMessage saved = chatMessageService.save(chatMessage);
        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(chatMessage.getChatroom().getId()),"/room/{chatroomId}/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId()));
    }

    private void setChatroomId(ChatMessage chatMessage) {
        Optional<ChatRoom> chatRoom = chatRoomService
                .findChatRoom(chatMessage.getItem().getItemId(), chatMessage.getSenderId(), true);  //chatroomId 클라이언트에서 받아서 서버 연결
        Long chatRoomId = chatRoom.get().getId();
        chatMessage.setId(chatRoomId);
    }

    @GetMapping("/messages/{senderId}/{receiverId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String senderId,
            @PathVariable String receiverId) {

        return ResponseEntity
                .ok(chatMessageService.countNewMessages(senderId, receiverId));
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String senderId,
                                                @PathVariable String receiverId) {
        return ResponseEntity
                .ok(chatMessageService.findChatMessages(senderId, receiverId));
    }

}