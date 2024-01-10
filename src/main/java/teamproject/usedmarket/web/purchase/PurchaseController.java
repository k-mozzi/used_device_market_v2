package teamproject.usedmarket.web.purchase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamproject.usedmarket.web.item.ItemUpdateDto;
import teamproject.usedmarket.service.item.ItemService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class PurchaseController {
    private final ItemService itemService;

    @GetMapping("/item/purchasePopup")
    public String purchasePop(@RequestParam Long itemId,
                              @RequestParam String title,
                              @RequestParam Integer price,
                              Model model) {
        model.addAttribute("title", title);
        model.addAttribute("price", price);
        model.addAttribute("itemId", itemId);

        return "popup/purchasePopup";
    }

    @ResponseBody
    @PostMapping("/updateValue")
    public ResponseEntity<String> updateValue(@RequestBody Map<String, String> requestBody, HttpSession session) throws IOException {
        Long memberId = (Long) session.getAttribute("memberId");
        log.info("memberId22={}", memberId);

        String itemId = requestBody.get("itemId");
        log.info("itemId = {}", itemId);
        Long id = Long.valueOf(itemId);
        ItemUpdateDto itemUpdateDto = new ItemUpdateDto(2, memberId, new Date());
        itemService.updateStatus(id, itemUpdateDto);


        return ResponseEntity.ok("Success");
    }
}
