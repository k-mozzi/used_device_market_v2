package teamproject.usedmarket.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import teamproject.usedmarket.domain.member.Member;
import teamproject.usedmarket.repository.MemberRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * 로그인
     */
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    /**
     * 회원가입
     */
    public String join(Member member) {
        //같은 이름이 있는 중복 회원x
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getLoginId();
    }

    /**
     * 중복회원
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByLoginId(member.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }


}
