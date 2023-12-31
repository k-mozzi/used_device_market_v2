package teamproject.usedmarket.domain.chat;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {

    private Long id;
    private String content;
    private String sender;
    private LocalDateTime createdAt;

    // 생성자, 게터, 세터 등 필요한 메소드들을 추가합니다.

}