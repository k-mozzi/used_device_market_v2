package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import teamproject.usedmarket.domain.chat.ChatMessage;


@Service
@RequiredArgsConstructor
public class ChatService {

    private final SimpMessagingTemplate messagingTemplate;


    public void sendMessage(ChatMessage chatMessage) {
        // 채팅 메시지 전송 로직
        // 이 메소드를 호출하여 채팅 메시지를 모든 구독자에게 전송
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }

    public void addUser(ChatMessage chatMessage) {
        // 사용자 추가 로직
        // 이 메소드를 호출하여 사용자 추가 메시지를 모든 구독자에게 전송
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}