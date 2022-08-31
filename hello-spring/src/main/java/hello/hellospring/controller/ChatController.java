package hello.hellospring.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hellospring.domain.ChatMessage;
import hello.hellospring.domain.ChatNotification;
import hello.hellospring.domain.ChatRoom;
import hello.hellospring.dto.ChatRequestDto;
import hello.hellospring.repository.ChatMessageRepository;
import hello.hellospring.service.ChatMessageService;
import hello.hellospring.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChatController {


    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final ChatMessageRepository chatMessageRepository;

    //판매자에게 채팅 처음 생성 시 채팅방 생성,저장
    @PostMapping("/api/chat/new")
    public ResponseEntity<?> createChatRoom(@RequestParam("receiverNo") int receiverNo,
                                            @RequestParam("itemId") int itemId,
                                            HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Long senderNo = (Long) session.getAttribute("memberNo");

        Optional<ChatRoom> chatRoom = chatRoomService.findChatRoom(Long.valueOf(itemId),senderNo, true);

        return new ResponseEntity<>(chatRoom, HttpStatus.OK);
    }

    //생성된 채팅에 진입, 채팅 기록 불러오기
    @GetMapping("/api/chat/{receiverId}/{chatroomId}")
    public ResponseEntity<?> enterChatRoom(@PathVariable Map<String, String> pathVarsMap,
                                  HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        String senderId = (String) session.getAttribute("memberId");
        String receiverId = pathVarsMap.get("receiverId");
        String chatroomId = pathVarsMap.get("chatroomId");

        List<ChatMessage> chatMessageList =  chatMessageRepository.findByChatroomId(Long.valueOf(chatroomId));

        return new ResponseEntity<>(chatMessageList, HttpStatus.OK);
    }



    @MessageMapping("/chat")  //여기로 전송되면 메서드 호출
    public void processMessage(@Payload String msg) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        ChatRequestDto chatRequestDto = mapper.readValue(msg, ChatRequestDto.class);
        chatRequestDto.setSendDateTime(LocalDateTime.now());

        System.out.print("메시지 저장  (수신자):"+chatRequestDto.getReceiverId());
        System.out.print(" / (채팅방아이디):"+chatRequestDto.getChatroomId());
        System.out.println(" / (content):"+chatRequestDto.getContent());


        //메시지 저장하기
        ChatMessage saved = chatMessageService.save(chatRequestDto);

        //수신 유저에게 메시지 수신 알림 보내기
        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(chatRequestDto.getReceiverId()),"/user/{receiverId}/queue/messages",
                new ChatNotification(
                        saved.getId(),
                        saved.getSenderId()));
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