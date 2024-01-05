package teamproject.usedmarket.web.purchase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemUpdateDto;
import teamproject.usedmarket.service.item.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping
@RequiredArgsConstructor
public class PurchaseController {


    private final ItemService itemService;

    @GetMapping("/parent")
    public String parent() {
        return "item/item";
    }



    @GetMapping("/item/purchasePopup")
    public String purchasePop(@RequestParam("parentValue") Long parentValue,
                              HttpSession session,Model model) {
        Long memberId = (Long) session.getAttribute("memberId");


        log.info("itemId2222={}",parentValue);
        log.info("start Popup");

        model.addAttribute("parentValue", parentValue);

        return "popup/purchasePopup";
    }




    @ResponseBody
    @PostMapping("/updateValue")
    public ResponseEntity<String> updateValue(@RequestBody Map<String, String> requestBody, HttpSession session) throws IOException {
        Long memberId = (Long) session.getAttribute("memberId");
        log.info("memberId22={}",memberId);

        String parentValue = requestBody.get("parentValue");
        log.info("itemId = {}",parentValue);
        Long id = Long.valueOf(parentValue);
        ItemUpdateDto itemUpdateDto = new ItemUpdateDto(2,memberId, new Date());
        itemService.updateStatus(id,itemUpdateDto);



        // 실제로 DB 업데이트하는 로직 수행 (ItemService를 호출하거나 직접 구현)
        // 예를 들어, itemService.updateValue(newValue);

        return ResponseEntity.ok("Success");
    }



}
