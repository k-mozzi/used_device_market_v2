package teamproject.usedmarket.web.itemimage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teamproject.usedmarket.service.image.ImageService;

@Controller
@Slf4j
//@RequestMapping("/image")
@RequiredArgsConstructor
public class ItemImageController {
    private final ImageService imageService;

    @PostMapping("/deleteImage/{itemImageId}")
    @ResponseBody
    public String deletePhoto(@PathVariable int itemImageId) {
        imageService.delete(itemImageId);
        return "deleted : "+itemImageId;
    }
}
