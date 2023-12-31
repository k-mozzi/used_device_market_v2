package teamproject.usedmarket.web.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import teamproject.usedmarket.domain.chat.ChatMessage;
import teamproject.usedmarket.service.ChatService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {


    private final ChatService chatService;

    @GetMapping("/chat")
    public String showChatPage(Model model) {
        List<ChatMessage> messages = chatService.getAllMessages();
        model.addAttribute("messages", messages);
        return "chat";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage handleChatMessage(ChatMessage message) {
        chatService.sendMessage(message);
        return message;
    }
}
