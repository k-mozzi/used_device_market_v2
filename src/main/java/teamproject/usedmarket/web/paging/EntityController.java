package teamproject.usedmarket.web.paging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.service.EntityServiceV1;
import teamproject.usedmarket.service.ImageService;
import teamproject.usedmarket.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EntityController {

    private final EntityServiceV1 entityService;
    private final ItemService itemService;
    private final ImageService imageService;

    @GetMapping("/entities")
    public String paging(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int pageSize,
                                  Model model) {
        List<Item> entities = entityService.getEntitiesWithPaging(page, pageSize);
        int totalPages = calculateTotalPages(pageSize);
        List<ItemImage> images = imageService.findImages();
        model.addAttribute("entities", entities);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("images", images);

        return "entities";

    }

    private int calculateTotalPages(int pageSize) {
        int totalEntities = entityService.countTotalEntities();
        return (int) Math.ceil((double) totalEntities / pageSize);
    }
}
