package hello.hellospring.controller;


import hello.hellospring.domain.ChatMessage;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MemberService memberService;

    //접속 Id 선택
    @GetMapping("/chat/new")
    public String createChatForm(Model model) throws Exception {
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);
        return "/chat/chatEnter";
    }

    @PostMapping("/chat/new")
    public String createChatRoom(Model model, @RequestParam("memberId") Long memberId) throws Exception {
        //선택된 Id로 찾은 Member 객체를 모델로 뷰에 전달
        Optional<Member> findMember = memberService.findOne(memberId);
        String member = findMember.get().getMemberName();
        model.addAttribute("sender", member);
        return "/chat/chatRoom";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }


    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // 웹소켓 세션에 username 추가
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;

    }
}