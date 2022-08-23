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
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MemberService memberService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private ChatRoomService chatRoomService;
    private ChatMessageService chatMessageService;

    @GetMapping("/chat/new")
    public String createChatRoom(Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);
        String senderId = (String) session.getAttribute("memberId");
        model.addAttribute("senderId",senderId);

        return "/chat/chatRoom";
    }



    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        setChatroomId(chatMessage);

        ChatMessage saved = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(),"/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId()));
    }

    private void setChatroomId(ChatMessage chatMessage) {
        Optional<ChatRoom> chatRoom = chatRoomService
                .findChatRoom(chatMessage.getItemId(), chatMessage.getSenderId(), true);
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