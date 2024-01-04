package teamproject.usedmarket.web.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.member.Member;
import teamproject.usedmarket.repository.MemberRepository;
import teamproject.usedmarket.service.ItemService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MemberRepository memberRepository;
    private final ItemService itemService;

//    @GetMapping("/item")
//    public String showChatPopup(@RequestParam("itemId") Long itemId, Model model, HttpSession session) {
//
//
//
//
//        // 현재 로그인한 사용자 정보 가져오기
//        Member buyer = (Member) session.getAttribute("loginMember");
//
//        // 아이템의 판매자 정보 가져오기 (아래 메서드는 예시일뿐 실제로는 itemService를 통해 아이템 정보를 가져와야 함)
//        Item item = itemService.findById(itemId).get();
//        Long sellerMemberId = item.getSellerMemberId();
//        Member seller = memberRepository.findByMemberId(sellerMemberId).get();
//
//        // 대화 상대 정보 모델에 추가
//        model.addAttribute("buyer", buyer);
//        model.addAttribute("seller", seller);
//
//        return "chatPopup";
//    }

//    @GetMapping("/chat/{roomId}")
//    public String chat(Model model, @PathVariable String roomId, HttpSession session) {
//        // 세션에서 현재 사용자 정보 가져오기
//        Member member = (Member) session.getAttribute("user");
//        model.addAttribute("user", member);
//
//        // 채팅방 정보 가져오기
////        Room room = chatService.getRoom(roomId);
////        model.addAttribute("room", room);
//
//        return "chat";
//    }


    @PostMapping("/openChatPopup")
    public String openChatPopup(@RequestParam("sellerId") String sellerId,
                                             @RequestParam("buyerId") String buyerId,
                                Model model) {
        // 여기에서 sellerId와 buyerId를 이용하여 채팅 창을 열거나 다른 작업을 수행
        model.addAttribute("sellerId", sellerId);
        model.addAttribute("buyerId", buyerId);

        return "chat/chatPopup";
    }


}
