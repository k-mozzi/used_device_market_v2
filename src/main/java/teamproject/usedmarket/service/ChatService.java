package teamproject.usedmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teamproject.usedmarket.domain.chat.ChatMessage;
import teamproject.usedmarket.repository.mybatis.ChatMapper;

import java.util.List;

@Service
public class ChatService {

    private final ChatMapper chatMapper;

    @Autowired
    public ChatService(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
    }

    public void saveMessage(ChatMessage message) {
        chatMapper.insertMessage(message);
    }

    public List<ChatMessage> getLatestMessages(int limit) {
        return chatMapper.getLatestMessages(limit);
    }
}