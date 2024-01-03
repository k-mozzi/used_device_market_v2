package teamproject.usedmarket.domain.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Item {

    private Long itemId;
    private int itemTypeId;
    @NotBlank
    @Size(min = 1, max = 30)
    private String title;
    @NotBlank
    @Size(min = 1, max = 1024)
    private String content;
    @NotNull
    @Range(min = 1, max = 5000000)
    private Integer price;
    private int saleStatus;
    private Long sellerMemberId;
    private Long buyerMemberId;
    private int viewsCount;
    private String repImagePath;
    private Date createDatetime;
    private Date updateDatetime;

    private String filename;
    private String filepath;

    private BigDecimal latitude; // 위도
    private BigDecimal longitude; // 경도

    public Item(int itemTypeId, String title, String content, Integer price, int saleStatus,
                Long sellerMemberId, Long buyerMemberId, int viewsCount, String repImagePath,
                Date createDatetime, Date updateDatetime, BigDecimal latitude, BigDecimal longitude) {
        this.itemTypeId = itemTypeId;
        this.title = title;
        this.content = content;
        this.price = price;
        this.saleStatus = saleStatus;
        this.sellerMemberId = sellerMemberId;
        this.buyerMemberId = buyerMemberId;
        this.viewsCount = viewsCount;
        this.repImagePath = repImagePath;
        this.createDatetime = createDatetime;
        this.updateDatetime = updateDatetime;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}