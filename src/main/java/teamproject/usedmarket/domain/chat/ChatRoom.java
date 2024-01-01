package teamproject.usedmarket.domain.chat;


import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
public class ChatRoom {
    private String roomId;
    private Long itemId;
    private Long memberId;
    private String name;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }
}
