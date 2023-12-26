package teamproject.usedmarket.web.itemimage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.domain.item.ItemLike;
import teamproject.usedmarket.service.ImageService;
import teamproject.usedmarket.web.SendDto;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
//@RequestMapping("/image")
@RequiredArgsConstructor
public class ItemImageController {

    private final ImageService imageService;


    @GetMapping("/photos")
    public String showPhotos() {
        return "photos";
    }

    @DeleteMapping("/delete/{filename}")
    @ResponseBody
    public String deletePhoto(@PathVariable String filename) {
        // 파일을 삭제하는 로직을 여기에 추가
        // 이 예제에서는 파일이 삭제되었다고 가정하고 성공 메시지를 반환
        return "Deleted: " + filename;
    }


}
