package teamproject.usedmarket.domain.chat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * 채팅
 */
@Data
@NoArgsConstructor
public class Chat {
    private Long chatId;
    private Long roomId;
    private String message;
    private Long memberId; // sender_id를 member_id로 변경
    private LocalDateTime timestamp;

}
