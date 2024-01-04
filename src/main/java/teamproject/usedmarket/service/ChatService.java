//package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import teamproject.usedmarket.domain.chat.Chat;
import teamproject.usedmarket.repository.mybatis.ChatMapper;

import java.util.List;

//
//@Service
//@RequiredArgsConstructor
//public class ChatService {
//
//    private final ChatMapper chatMapper;
//
//
//    public void saveMessage(Chat chat) {
//        chatMapper.saveMessage(chat);
//    }
//
//    public List<Chat> getAllChats() {
//        return chatMapper.getAllChats();
//    }
//
//
//}