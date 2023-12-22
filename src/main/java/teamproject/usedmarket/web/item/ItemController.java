package teamproject.usedmarket.web.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemType;
import teamproject.usedmarket.domain.item.SaleStatus;
import teamproject.usedmarket.repository.ItemUpdateDto;
import teamproject.usedmarket.service.ItemService;

import javax.servlet.http.HttpSession;
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
        if (item != null) {
            model.addAttribute("item", item);
            model.addAttribute("selectedItemTypeId", item.getItemTypeId());
            model.addAttribute("itemTypes", ItemType.values());
            model.addAttribute("selectedSaleStatus", item.getSaleStatus());
            model.addAttribute("statuses", SaleStatus.values());
            itemService.incrementViewsCount(itemId);
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
    public String addItem(@ModelAttribute Item item, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        item.setCreateDatetime(new Date());
        itemService.save(item, file, session);
        return "redirect:/items";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Item item = itemService.findById(itemId).get();

        //셀러 아이디와 로그인 아이디가 동일할 때만 접근 가능! 일단 아이템 등록할 때 셀러 멤버 아이디 바인딩 해야 함
        Long currentMemberId = (Long) session.getAttribute("memberId");
        Long sellerMemberId = item.getSellerMemberId();
        if (!currentMemberId.equals(sellerMemberId)) {
            log.info("아이디가 달라용");
            redirectAttributes.addFlashAttribute("error", "타인의 게시물은 수정할 수 없습니다.");
            return "redirect:/items";
        }

        model.addAttribute("item", item);
        model.addAttribute("itemTypes", ItemType.values());
        model.addAttribute("statuses", SaleStatus.values());
        return "item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute ItemUpdateDto updateParam, @RequestParam("file") MultipartFile file) throws IOException {
        updateParam.setUpdateDatetime(new Date());
        itemService.update(itemId, updateParam, file);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/delete")
    public String delete(@PathVariable Long itemId, HttpSession session, RedirectAttributes redirectAttributes) {
        Item item = itemService.findById(itemId).get();

        Long currentMemberId = (Long) session.getAttribute("memberId");
        Long sellerMemberId = item.getSellerMemberId();
        if (!currentMemberId.equals(sellerMemberId)) {
            log.info("아이디가 달라용");
            redirectAttributes.addFlashAttribute("error", "타인의 게시물은 삭제할 수 없습니다.");
            return "redirect:/items";
        }

        itemService.delete(itemId);
        log.info("delete itemId={}", itemId);
        return "redirect:/items";
    }

}