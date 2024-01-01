package teamproject.usedmarket.web.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamproject.usedmarket.domain.chat.ChatRoom;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ChatRoomRepository;
import teamproject.usedmarket.service.ItemService;
import teamproject.usedmarket.service.ItemServiceV1;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    //나중에 복잡해지면 service, repository 분리하겠지만 아직은..
    private final ChatRoomRepository chatRoomRepository;

    // 채팅 리스트 화면
    @GetMapping("/roomList")
    public String rooms(Model model) {
        return "/chat/roomList";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomRepository.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomRepository.createChatRoom(name);
    }

    // 채팅방 입장 화면   단순히 화면으로 이동
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomDetail";
    }


    // 특정 채팅방 조회     axios로 방 찾을 때의 uri.    subscribe()의 uri랑 상관없음
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}