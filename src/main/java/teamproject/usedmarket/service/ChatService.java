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
//    public List<ChatMessage> getChatMessagesByItemId(Long itemId) {
//        // itemId를 사용하여 해당 아이템의 채팅 내용을 조회하는 로직 작성
//        // 실제로는 ChatMessageRepository 등을 사용하여 데이터베이스에서 채팅 내용을 조회해야 함
//        // 아래는 가상의 예시 코드
//        return chatMessageRepository.findByItemId(itemId);
//    }

    public void addUser(ChatMessage chatMessage) {
        // 사용자 추가 로직
        // 이 메소드를 호출하여 사용자 추가 메시지를 모든 구독자에게 전송
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}