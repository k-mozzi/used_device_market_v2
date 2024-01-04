package teamproject.usedmarket.web.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamproject.usedmarket.domain.item.*;
import teamproject.usedmarket.domain.member.Member;
import teamproject.usedmarket.repository.ItemUpdateDto;
import teamproject.usedmarket.repository.MemberRepository;
import teamproject.usedmarket.service.ImageService;
import teamproject.usedmarket.service.ItemService;
import teamproject.usedmarket.service.LikeService;

import javax.servlet.http.HttpSession;
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
    private final LikeService likeService;
    private final MemberRepository memberRepository;

    @GetMapping
    public String items(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "registrationDate") String sort,
                        @RequestParam(required = false) String itemType,
                        @RequestParam(required = false) String regionId,
                        @RequestParam(required = false) String searchText,
                        Model model) {

        int pageSize = 10; // 페이지당 아이템 수
        List<Item> items;

        // 검색어에 따른 아이템 조회
        if (searchText != null && !searchText.isEmpty()) {
            items = itemService.findItemsWithPagingAndSearch(page, pageSize, searchText);
        } else {
            // 정렬 방식에 따른 아이템 조회
            switch (sort) {
                case "registrationDate":
                    items = itemService.findItemsSortedByRegistrationDate(page, pageSize, itemType, regionId);
                    break;
                case "viewsCount":
                    items = itemService.findItemsSortedByViewsCount(page, pageSize, itemType, regionId);
                    break;
                case "likesCount":
                    items = itemService.findItemsSortedByLikesCount(page, pageSize, itemType, regionId);
                    break;
                default:
                    items = itemService.findItemsWithPaging(page, pageSize, itemType, regionId);
                    break;
            }
        }

        // 페이징 처리를 위한 정보 전달
        int totalCount = itemService.countItems();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        List<ItemImage> images = imageService.findImages();
        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("images", images);
        model.addAttribute("sort", sort);
        model.addAttribute("itemType", itemType);
        model.addAttribute("regionId", regionId);
        model.addAttribute("searchText", searchText);

        return "item/items";
    }


    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model, HttpSession session) {
        Item item = itemService.findById(itemId).get();
        Long sellerMemberId = item.getSellerMemberId();
        List<ItemImage> itemImages = imageService.findByItemId(itemId);
        String foundMemberName = itemService.findMemberNameBySellerMemberId(sellerMemberId, itemId);
        Long memberId = (Long) session.getAttribute("memberId");
        boolean isLiked = likeService.existsByMemberIdAndItemId(memberId, itemId);
        itemService.incrementViewsCount(itemId);

        // 현재 로그인한 사용자 정보 가져오기
        Member member = memberRepository.findByMemberId(memberId).get();

        String buyer = member.getMemberName();
        String seller = foundMemberName;

        // 대화 상대 정보 모델에 추가
        model.addAttribute("buyer", buyer);
        model.addAttribute("seller", seller);
        model.addAttribute("item", item);
        model.addAttribute("selectedItemTypeId", item.getItemTypeId());
        model.addAttribute("itemTypes", ItemType.values());
        model.addAttribute("selectedSaleStatus", item.getSaleStatus());
        model.addAttribute("statuses", SaleStatus.values());
        model.addAttribute("itemImages", itemImages);
        model.addAttribute("foundMemberName", foundMemberName);
        model.addAttribute("currentMemberId", memberId);
        model.addAttribute("currentItemId", itemId);
        model.addAttribute("isLiked", isLiked);
        // 추가: 마커의 위도와 경도를 모델에 추가
        model.addAttribute("latitude", item.getLatitude());
        model.addAttribute("longitude", item.getLongitude());

        return "item/item";
    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute Item item, HttpSession session, Model model) {

        Long memberId = (Long) session.getAttribute("memberId");
        Member foundMember = memberRepository.findByMemberId(memberId).get();
        int regionId = foundMember.getRegionId();

        model.addAttribute("regionId", regionId);
        model.addAttribute("item", new Item());
        model.addAttribute("itemTypes", ItemType.values());
        model.addAttribute("statuses", SaleStatus.values());
        return "item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute Item item, BindingResult bindingResult, @RequestParam("imageFiles") List<MultipartFile> file,
                          HttpSession session, RedirectAttributes redirectAttributes, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            model.addAttribute("itemTypes", ItemType.values());
            model.addAttribute("statuses", SaleStatus.values());
            return "item/addForm";
        }

        item.setCreateDatetime(new Date());
        // 위도와 경도 설정
        item.setLatitude(item.getLatitude());
        item.setLongitude(item.getLongitude());
        log.info("getLatitude={}", item.getLatitude());
        log.info("getLatitude={}", item.getLongitude());
        Item savedItem = itemService.save(item, session);
        redirectAttributes.addAttribute("itemId", savedItem.getItemId());
        imageService.save(savedItem.getItemId(), file);

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Item item = itemService.findById(itemId).get();
        List<ItemImage> itemImages = imageService.findByItemId(itemId);


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
        model.addAttribute("itemImages", itemImages);
        return "item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute ItemUpdateDto updateParam,
                       @RequestParam("imageFiles") List<MultipartFile> file) throws IOException {
        List<ItemImage> itemImages = imageService.findByItemId(itemId);

        updateParam.setUpdateDatetime(new Date());
        itemService.update(itemId, updateParam);
        imageService.save(itemId, file);
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


    /**
     * 관심 상품 등록
     */
    @PostMapping("/{itemId}/addInterest")
    public ResponseEntity<String> addInterest(@PathVariable Long itemId, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        ItemLike itemLike = new ItemLike(memberId, itemId);
        likeService.addInterest(itemLike);
        return ResponseEntity.ok("success");
    }

    /**
     * 관심 상품 삭제
     */
    @PostMapping("/{itemId}/removeInterest")
    public ResponseEntity<String> removeInterest(@PathVariable Long itemId, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        ItemLike itemLike = new ItemLike(memberId, itemId);
        likeService.removeInterest(itemLike);
        return ResponseEntity.ok("success");
    }

    /**
     * 테이블에 관심 상품이 존재하는지 여부 확인
     */
    @GetMapping("/{itemId}/interestStatus")
    @ResponseBody
    public ResponseEntity<String> getInterestStatus(@PathVariable Long itemId, HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        boolean exists = likeService.existsByMemberIdAndItemId(memberId, itemId);
        return ResponseEntity.ok(exists ? "exists" : "not_exists");
    }

    /**
     * 관심상품으로 등록한 회읜의 수
     */
    @GetMapping("/{itemId}/totalLikedItem")
    public ResponseEntity<Integer> totalLikedItem(@PathVariable Long itemId) {
        // 아이템의 찜 수 조회 로직 수행
        int totalLikedItem = likeService.totalLikedItem(itemId);
        return ResponseEntity.ok(totalLikedItem);
    }

}