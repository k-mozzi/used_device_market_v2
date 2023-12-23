package teamproject.usedmarket.web.image;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamproject.usedmarket.repository.ItemUpdateDto;
import teamproject.usedmarket.service.ImageService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequestMapping("/image")
@Controller
@RequiredArgsConstructor
public class ItemImageController {

    private final ImageService imageService;

    @GetMapping("/{itemImageId}/delete")
    public String delete(@PathVariable Long itemImageId) throws IOException {
        imageService.delete(itemImageId);
        return "redirect:/items/{itemId}/edit";
    }


}
