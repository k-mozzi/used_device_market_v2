//package teamproject.usedmarket.web.chat;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import teamproject.usedmarket.domain.chat.ChatMessage;
//
//import java.security.Principal;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Controller
//@RequiredArgsConstructor
//public class ChatController {
//
//    private final SimpMessagingTemplate messagingTemplate;
//    private ChatService chatService;
//
//
//    @GetMapping("/chat/chatPopup")
//    public String chatPopup() {
//        return "chat/chatPopup";
//    }
//    @MessageMapping("/chat.openChat")
//    public void openChat(@Payload OpenChatRequest request, Principal principal) {
//        String buyer = principal.getName(); // 현재 로그인한 사용자 (구매자)
//        String seller = request.getSeller(); // 채팅 상대방 (판매자)
//
//        // 채팅 ID 생성 (이 부분은 실제로는 더 안전한 방식으로 생성되어야 함)
//        String chatId = buyer + "-" + seller;
//
//        // 사용자와 채팅 ID 매핑 저장
//        userToChatIdMap.put(buyer, chatId);
//        userToChatIdMap.put(seller, chatId);
//
//        // 클라이언트에 채팅 ID 전송
//        messagingTemplate.convertAndSendToUser(buyer, "/queue/chatId", chatId);
//        messagingTemplate.convertAndSendToUser(seller, "/queue/chatId", chatId);
//    }
//
//
//    @MessageMapping("/chat.sendMessage")
//    public void sendMessage(@Payload ChatMessage chatMessage, Principal principal) {
//        String chatId = userToChatIdMap.get(principal.getName());
//
//        if (chatId != null) {
//            // 채팅 ID가 존재하면 해당 채팅 방으로 메시지 전송
//            messagingTemplate.convertAndSend("/topic/" + chatId, chatMessage);
//        }
//    }
//
//    @MessageMapping("/chat.addUser")
//    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        // 사용자 추가 로직
//    }
//}
