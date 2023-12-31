package teamproject.usedmarket.service;

import teamproject.usedmarket.domain.chat.ChatMessage;

import java.util.List;

public interface ChatService {
    List<ChatMessage> getAllMessages();

    void sendMessage(ChatMessage message);
}
