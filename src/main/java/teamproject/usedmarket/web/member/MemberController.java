package teamproject.usedmarket.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import teamproject.usedmarket.SessionConst;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.domain.member.Region;
import teamproject.usedmarket.repository.MemberRepository;
import teamproject.usedmarket.service.ImageService;
import teamproject.usedmarket.service.LikeService;
import teamproject.usedmarket.service.LoginService;
import teamproject.usedmarket.domain.member.Member;

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
            return "home";
        } catch (IllegalStateException e) {
            bindingResult.reject("join fail", e.getMessage());
            log.error("중복 아이디 예외 발생: {}", e.getMessage());
            model.addAttribute("regions", Region.values());
            return "members/addMemberForm";
        }
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = loginService.findMembers();
        model.addAttribute("members", members);
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
    public String memberSell(@PathVariable long memberId, Model model) {

        List<Item> soldItems = memberRepository.findSellItemByMemberId(memberId);
        List<ItemImage> images = imageService.findImages();
        model.addAttribute("soldItems", soldItems);
        model.addAttribute("images", images);
        return "members/myPage/memberSell";
    }

}
