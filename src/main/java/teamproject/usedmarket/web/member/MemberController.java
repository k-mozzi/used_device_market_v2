package teamproject.usedmarket.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import teamproject.usedmarket.domain.login.LoginService;
import teamproject.usedmarket.domain.member.Member;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final LoginService loginService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String join(@Validated @ModelAttribute Member member, BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()) {
                log.info("error={}", bindingResult);
                return "members/addMemberForm";
            }

            loginService.join(member);
            return "home";
        } catch (IllegalStateException e) {
            bindingResult.reject("join fail", e.getMessage());
            log.error("중복 아이디 예외 발생: {}", e.getMessage());
            return "members/addMemberForm";
        }

    }
}
