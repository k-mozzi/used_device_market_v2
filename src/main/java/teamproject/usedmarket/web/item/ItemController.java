package teamproject.usedmarket.web.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.domain.item.ItemType;
import teamproject.usedmarket.domain.item.SaleStatus;
import teamproject.usedmarket.repository.ItemUpdateDto;
import teamproject.usedmarket.service.ImageService;
import teamproject.usedmarket.service.ItemService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ImageService imageService;



    @GetMapping
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        List<ItemImage> images = imageService.findImages();
        model.addAttribute("items", items);
        model.addAttribute("images", images);
        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemService.findById(itemId).get();
        if (item != null) {
            model.addAttribute("item", item);
            model.addAttribute("selectedItemTypeId", item.getItemTypeId());
            model.addAttribute("itemTypes", ItemType.values());
            model.addAttribute("selectedSaleStatus", item.getSaleStatus());
            model.addAttribute("statuses", SaleStatus.values());
            return "item/item";
        } else {
            // 아이템이 존재하지 않는 경우에 대한 예외 처리
            return "redirect:/items";
        }
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute Item item, Model model) {
        model.addAttribute("itemTypes", ItemType.values());
        model.addAttribute("statuses", SaleStatus.values());
        return "item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item,@RequestParam("imageFiles") List<MultipartFile> file,Model model) throws IOException {


        item.setCreateDatetime(new Date());
        Item saveditem = itemService.save(item,null);


        log.info("id value = {}",saveditem.getItemId());
        ItemImage savedImage = imageService.save(saveditem.getItemId(), file);


        return "redirect:/items";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findById(itemId).get();
        model.addAttribute("item", item);
        model.addAttribute("itemTypes", ItemType.values());
        model.addAttribute("statuses", SaleStatus.values());
        return "item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute ItemUpdateDto updateParam,@RequestParam("file") MultipartFile file) throws IOException {
        updateParam.setUpdateDatetime(new Date());
        itemService.update(itemId, updateParam, file);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId) {
        itemService.delete(itemId);
        log.info("delete itemId={}", itemId);
        return "redirect:/items";
    }

}