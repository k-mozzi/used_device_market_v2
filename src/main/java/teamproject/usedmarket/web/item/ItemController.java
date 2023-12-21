package teamproject.usedmarket.web.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemType;
import teamproject.usedmarket.domain.item.SaleStatus;
import teamproject.usedmarket.service.ItemService;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemService.findById(itemId).get();
        model.addAttribute("item", item);
        return "item/item";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute Item item, Model model) {
        model.addAttribute("statuses", SaleStatus.values());
        model.addAttribute("itemTypes", ItemType.values());
        return "item/addForm";
    }


    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        item.setCreateDatetime(new Date());
        item.setUpdateDatetime(new Date());
        itemService.save(item, file);
        redirectAttributes.addAttribute("items", item.getItemId());
        return "redirect:/items";
    }

    @GetMapping("/img/product/{img_save_name}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable ("img_save_name") String imgSaveName) throws IOException{
        Resource resource = new FileSystemResource("C:\\Users\\82109\\"+imgSaveName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findById(itemId).get();
        model.addAttribute("item", item);
        return "item/editForm";
    }

//    @PostMapping("/{itemId}/edit")
//    public String edit(@PathVariable Long itemId, @ModelAttribute ItemUpdateDto updateParam, MultipartFile file) {
//        itemService.update(itemId, updateParam, file);
//        return "redirect:item/items/{itemId}";
//    }


}