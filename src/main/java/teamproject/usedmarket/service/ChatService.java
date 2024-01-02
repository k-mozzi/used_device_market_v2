package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatService {

    private SimpMessagingTemplate messagingTemplate;

    // 채팅 서비스 로직
}