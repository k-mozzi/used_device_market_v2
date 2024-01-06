package teamproject.usedmarket.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamproject.usedmarket.SessionConst;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.domain.item.ItemType;
import teamproject.usedmarket.domain.item.SaleStatus;
import teamproject.usedmarket.domain.member.Region;
import teamproject.usedmarket.repository.ItemUpdateDto;
import teamproject.usedmarket.repository.MemberRepository;
import teamproject.usedmarket.repository.MemberUpdateDto;
import teamproject.usedmarket.service.image.ImageService;
import teamproject.usedmarket.service.item.ItemService;
import teamproject.usedmarket.service.like.LikeService;
import teamproject.usedmarket.service.login.LoginService;
import teamproject.usedmarket.domain.member.Member;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final LoginService loginService;
    private final LikeService likeService;
    private final ImageService imageService;
    private final MemberRepository memberRepository;
    private final ItemService itemService;



    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("regions", Region.values());
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String join(@Validated @ModelAttribute Member member, BindingResult bindingResult, Model model) {

        try {
            if (bindingResult.hasErrors()) {
                log.info("error={}", bindingResult);
                model.addAttribute("regions", Region.values());
                return "members/addMemberForm";
            }

            member.setCreateDatetime(new Date());

            loginService.join(member);
            return "redirect:/";
        } catch (IllegalStateException e) {
            bindingResult.reject("join fail", e.getMessage());
            log.error("중복 아이디 예외 발생: {}", e.getMessage());
            model.addAttribute("regions", Region.values());
            return "members/addMemberForm";
        }
    }

    @GetMapping("/{memberId}/edit")
    public String editForm(@PathVariable Long memberId, Model model) {
        Member member = memberRepository.findByMemberId(memberId).get();

        model.addAttribute("member", member);
        model.addAttribute("regions", Region.values());
        return "members/editMemberForm";
    }

    @PostMapping("/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @ModelAttribute MemberUpdateDto updateParam, HttpSession session) {
        Member member = memberRepository.findByMemberId(memberId).get();
        memberRepository.update(memberId, updateParam);

        // 업데이트된 회원 정보를 다시 가져와 세션을 갱신
        Member updatedMember = memberRepository.findByMemberId(memberId).get();
        session.removeAttribute(SessionConst.LOGIN_MEMBER);
        session.setAttribute(SessionConst.LOGIN_MEMBER, updatedMember);
        session.setAttribute("memberId", updatedMember.getMemberId());

        return "redirect:/members/myPage/{memberId}";
    }

    @GetMapping("/{memberId}/delete")
    public String delete(@PathVariable Long memberId, HttpSession session) {
        Member member = memberRepository.findByMemberId(memberId).get();

        memberRepository.delete(memberId);
        //회원 탈퇴시 세션 삭제
        session.removeAttribute(SessionConst.LOGIN_MEMBER);

        return "redirect:/";
    }

    @GetMapping
    public String members(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10; // 한 페이지에 보여줄 아이템 수
        List<Item> members = memberRepository.findMembersWithPaging(page, pageSize);
        model.addAttribute("members", members);

        // 페이징 처리를 위한 정보 전달
        int totalCount = memberRepository.countItems();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        List<ItemImage> images = imageService.findImages();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("images", images);
        log.info("totalPage={}", totalPages);

        return "members/members";
    }

    @GetMapping("myPage")
    public String myPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member, Model model) {
        model.addAttribute("member", member);
        return "members/myPage";
    }

    /**
     * 회원 상세
     */
    @GetMapping("/myPage/{memberId}")
    public String memberInfo(@PathVariable long memberId, Model model) {

        Member member = loginService.findOne(memberId).get();
        model.addAttribute("member", member);
        model.addAttribute("selectedRegionId", member.getRegionId());
        model.addAttribute("regions", Region.values());
        return "members/myPage/memberInfo";
    }

    @GetMapping("/myPage/{memberId}/like")
    public String memberLike(@PathVariable long memberId, Model model) {

        List<Item> likedItems = likeService.findLikedItemByMemberId(memberId);
        List<ItemImage> images = imageService.findImages();
        model.addAttribute("likedItems", likedItems);
        model.addAttribute("images", images);
        return "members/myPage/memberLike";
    }

    @GetMapping("/myPage/{memberId}/sell")
    public String memberSell(@PathVariable long memberId, @RequestParam(defaultValue = "onSale") String sort, Model model) {

        List<Item> soldItems;

        // 정렬 방식에 따라 아이템을 가져옴
        switch (sort) {
            case "onSale":
                soldItems = memberRepository.findItemsSortedOnSale(memberId);
                break;
            case "soldOut":
                soldItems = memberRepository.findItemsSortedSoldOut(memberId);
                break;
            default:
                soldItems = memberRepository.findItemsSorted(memberId);
                break;
        }

        List<ItemImage> images = imageService.findImages();
        model.addAttribute("soldItems", soldItems);
        model.addAttribute("images", images);
        model.addAttribute("sort", sort); // 현재 정렬 방식 전달
        return "members/myPage/memberSell";
    }
@GetMapping("/myPage/{memberId}/buy")
    public String memberBuy(@PathVariable long memberId, Model model) {

    List<Item> buyItems = itemService.findByBuyerId(memberId);
log.info("buyItems = {}",buyItems);

    for (Item buyItem : buyItems) {
        log.info("buyItem = {}",buyItem);
    }

    List<ItemImage> images = imageService.findImages();
        model.addAttribute("buyItems", buyItems);
        model.addAttribute("images", images);
        return "members/myPage/memberBuy";
    }

}


