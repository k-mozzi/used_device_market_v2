package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.*;
import teamproject.usedmarket.domain.chat.ChatMessage;

import java.util.List;

@Mapper
public interface ChatMapper {

    @Insert("INSERT INTO chat_message (sender, content, timestamp) VALUES (#{sender}, #{content}, #{timestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertMessage(ChatMessage message);

    @Select("SELECT * FROM chat_message ORDER BY timestamp DESC LIMIT #{limit}")
    List<ChatMessage> getLatestMessages(@Param("limit") int limit);
}
