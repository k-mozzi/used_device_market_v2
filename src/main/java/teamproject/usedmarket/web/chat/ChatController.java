package teamproject.usedmarket.web.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import teamproject.usedmarket.domain.chat.ChatMessage;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.member.Member;
import teamproject.usedmarket.repository.MemberRepository;
import teamproject.usedmarket.service.ChatService;
import teamproject.usedmarket.service.ItemService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ItemService itemService;
    private final MemberRepository memberRepository;

    @GetMapping("/chat/chatPopup")
    public String chatPopup(@RequestParam Long itemId, Model model) {
        // 아이템 정보 및 대화 상대방 정보 가져오는 로직
        Item item = itemService.findById(itemId).get();
        Long sellerMemberId = item.getSellerMemberId();
        Member member = memberRepository.findByMemberId(sellerMemberId).get();
        String sellerName = member.getMemberName();

        // 대화 상대방 정보를 Thymeleaf 모델에 추가
        model.addAttribute("sellerName", sellerName); // 적절한 정보로 대체

        return "chatPopup";
    }

    @MessageMapping("/chat.openChat")
    public void openChat(@Payload OpenChatRequest request, Principal principal) {

        String seller = request.getSeller(); // 채팅 상대방 (판매자)

        // 채팅 ID 생성 (이 부분은 실제로는 더 안전한 방식으로 생성되어야 함)


        // 사용자와 채팅 ID 매핑 저장

        // 클라이언트에 채팅 ID 전송


    }


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // 사용자 정보를 WebSocket 세션에 추가
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }


}
