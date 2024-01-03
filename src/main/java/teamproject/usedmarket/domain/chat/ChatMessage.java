package teamproject.usedmarket.domain.chat;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ChatMessage {
    private Long id;
    private Long itemId;
    private String content;
    private String sender;
    private Date timestamp;

    // Getter, Setter, Constructor
}

