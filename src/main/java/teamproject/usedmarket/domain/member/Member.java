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
    @Size(min = 4, max = 15)
    private String loginId;

    @NotBlank
    @Size(min = 2, max = 10)
    private String memberName;

    @NotNull
    @Size(min = 6, max = 15)
    private String password;

    private int regionId;

    private Date createDatetime;

    private Date updateDatetime;

    public Member() {
    }

    public Member(String loginId, String memberName, String password) {
        this.loginId = loginId;
        this.memberName = memberName;
        this.password = password;
    }

    public Member(String loginId, String memberName, String password, int regionId, Date createDatetime, Date updateDatetime) {
        this.loginId = loginId;
        this.memberName = memberName;
        this.password = password;
        this.regionId = regionId;
        this.createDatetime = createDatetime;
        this.updateDatetime = updateDatetime;
    }

}
