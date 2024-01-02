package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.*;
import teamproject.usedmarket.domain.chat.ChatMessage;

import java.util.List;

@Mapper
public interface ChatMapper {


    void insertMessage(ChatMessage message);

    List<ChatMessage> getLatestMessages(@Param("limit") int limit);
}
