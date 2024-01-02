package teamproject.usedmarket.domain.chat;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessage {
    private Long id;
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    // Getter, Setter, Constructor
}

