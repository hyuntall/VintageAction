package hello.hellospring.controller;


import hello.hellospring.domain.ChatMessage;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final MemberService memberService;

    //접속 Id 선택
    @GetMapping("/chat/new")
    public String createChatForm(Model model) throws Exception {
        List<Member> members = memberService.findAll();
        model.addAttribute("members",members);
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