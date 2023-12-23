package teamproject.usedmarket.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.domain.item.ItemType;
import teamproject.usedmarket.domain.item.SaleStatus;
import teamproject.usedmarket.domain.member.Region;
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

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member, Model model) {
        model.addAttribute("regions", Region.values());
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String join(@Validated @ModelAttribute Member member, BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()) {
                log.info("error={}", bindingResult);
                return "members/addMemberForm";
            }

            member.setCreateDatetime(new Date());
            member.setUpdateDatetime(new Date());

            loginService.join(member);
            return "home";
        } catch (IllegalStateException e) {
            bindingResult.reject("join fail", e.getMessage());
            log.error("중복 아이디 예외 발생: {}", e.getMessage());
            return "members/addMemberForm";
        }
    }

    @GetMapping
    public String members(Model model) {
        List<Member> members = loginService.findMembers();
        model.addAttribute("members", members);
        return "members/members";
    }

    @GetMapping("/{memberId}")
    public String memberInfo(@PathVariable long memberId, Model model) {

        Member member = loginService.findOne(memberId).get();
        model.addAttribute("member", member);
        model.addAttribute("selectedRegionId", member.getRegionId());
        model.addAttribute("regions", Region.values());
        return "members/memberInfo";
    }

}
