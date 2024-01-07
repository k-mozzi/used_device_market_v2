package teamproject.usedmarket.repository;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MemberUpdateDto {
    @NotBlank
    @Size(min = 2, max = 10)
    private String memberName;
    @NotNull
    @Size(min = 6, max = 15)
    private String password;
    private int regionId;
}
