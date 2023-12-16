package teamproject.usedmarket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import teamproject.usedmarket.domain.member.Member;
import teamproject.usedmarket.repository.MemberRepository;
import teamproject.usedmarket.web.login.LoginForm;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

//    @GetMapping("/")
    public String home() {
        return "home";
    }
//주석추가
    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {

        if (memberId == null) {
            return "home";
        }

        //로그인 성공 사용자
        Optional<Member> loginMemberOptional = memberRepository.findByMemberId(memberId);
        if (loginMemberOptional.isEmpty()) {
            return "home";
        }

        Member loginMember = loginMemberOptional.get();
        model.addAttribute("loginForm", loginMember);
        return "loginHome";
    }
}
