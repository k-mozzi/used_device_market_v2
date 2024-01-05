package teamproject.usedmarket.web.purchase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import teamproject.usedmarket.domain.item.Item;

@Controller
@Slf4j
@RequestMapping
public class PurchaseController {

    @GetMapping("/parent")
    public String parent() {
        return "item/item";
    }



    @GetMapping("/item/purchasePop")
    public String purchasePop(@RequestParam("itemPrice") int itemPrice,

                              Model model) {
        log.info("start Popup");

        model.addAttribute("itemPrice", itemPrice);

        return "popup/purchasePopup";
    }




}
