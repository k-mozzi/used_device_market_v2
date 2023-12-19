package teamproject.usedmarket.domain.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Member {

    private Long memberId;
    @NotBlank
    @Size(min=4, max=10)
    private String loginId;
    @NotBlank
    @Size(min=3, max=7)
    private String memberName;
    @NotNull
    @Size(min=6, max=12)
    private String password;

    public Member() {
    }

    public Member( String loginId, String memberName, String password) {
        this.loginId = loginId;
        this.memberName = memberName;
        this.password = password;
    }
}
