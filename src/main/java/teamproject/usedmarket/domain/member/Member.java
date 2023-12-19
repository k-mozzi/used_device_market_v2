package teamproject.usedmarket.domain.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

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
    private Region regionId;
    private Date joinDate;

    public Member() {
    }

    public Member( String loginId, String memberName, String password) {
        this.loginId = loginId;
        this.memberName = memberName;
        this.password = password;
    }

    public Member(String loginId, String memberName, String password, Region regionId, Date joinDate) {
        this.loginId = loginId;
        this.memberName = memberName;
        this.password = password;
        this.regionId = regionId;
        this.joinDate = joinDate;
    }
}
