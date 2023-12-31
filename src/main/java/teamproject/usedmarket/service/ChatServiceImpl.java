package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamproject.usedmarket.domain.chat.ChatMessage;
import teamproject.usedmarket.repository.mybatis.MessageMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{

    private final MessageMapper messageMapper;
    @Override
    public List<ChatMessage> getAllMessages() {
        return messageMapper.getAllMessages();
    }

    @Override
    public void sendMessage(ChatMessage message) {
        messageMapper.insertMessage(message);

    }
}
