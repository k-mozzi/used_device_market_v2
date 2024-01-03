package teamproject.usedmarket.web.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import teamproject.usedmarket.domain.chat.ChatMessage;
import teamproject.usedmarket.service.ChatService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;


    @GetMapping("/chat/chatPopup")
    public String chatPopup() {
        return "chat/chatPopup";
    }
    @MessageMapping("/chat.openChat")
    public void openChat(@Payload OpenChatRequest request, Principal principal) {

        String seller = request.getSeller(); // 채팅 상대방 (판매자)

        // 채팅 ID 생성 (이 부분은 실제로는 더 안전한 방식으로 생성되어야 함)


        // 사용자와 채팅 ID 매핑 저장

        // 클라이언트에 채팅 ID 전송


    }


    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage, Principal principal) {


    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // 사용자 추가 로직

        // 적절한 처리...

        // 사용자 추가 로직
        // 여기서 로그인한 사용자 정보를 Member 엔티티로부터 가져와 사용할 수 있음
        // 예: Member member = memberService.getLoggedInMember();

    }


}
