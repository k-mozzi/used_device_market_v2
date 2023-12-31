package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.*;
import teamproject.usedmarket.domain.chat.ChatMessage;

import java.util.List;

@Mapper
public interface MessageMapper {



    List<ChatMessage> getAllMessages();

    void insertMessage(ChatMessage message);
}
