package teamproject.usedmarket.web.itemimage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.service.ImageService;
import teamproject.usedmarket.web.SendDto;

@Controller
@Slf4j
//@RequestMapping("/image")
@RequiredArgsConstructor
public class ItemImageController {

    private final ImageService imageService;

    @PostMapping("/send")
    public String send(Model model, SendDto sendDto) {
        model.addAttribute("itemImageId", sendDto.getBal());
        return "item/editForm :: #resultDiv";
    }

}
