package teamproject.usedmarket.web.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import teamproject.usedmarket.domain.chat.ChatMessage;
import teamproject.usedmarket.service.ChatService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketHandler {

    private final ChatService chatService;
    private final ObjectMapper objectMapper;

    @GetMapping("/chat")
    public String hihi() {
        return "/chat/chat";
    }


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public String sendMessage(String jsonMessage) throws Exception {
        ChatMessage message = objectMapper.readValue(jsonMessage, ChatMessage.class);
        chatService.saveMessage(message);
        return objectMapper.writeValueAsString(message);
    }

    @MessageMapping("/chat.getMessages")
    public void getMessages(String limit) throws Exception {
        int messageLimit = Integer.parseInt(limit);
        List<ChatMessage> messages = chatService.getLatestMessages(messageLimit);
        String jsonMessages = objectMapper.writeValueAsString(messages);
        // Send messages to the client using WebSocket (e.g., convert to JSON and send)
        // For simplicity, you can handle it based on your frontend requirements.
    }
}
