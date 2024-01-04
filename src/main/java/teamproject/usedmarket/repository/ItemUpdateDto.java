package teamproject.usedmarket.repository;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ItemUpdateDto {

    private String title;
    private String content;
    private Integer price;
    private int itemTypeId;
    private int saleStatus;
//    private Long buyerMemberId;
//    private int viewsCount;
    private Date updateDatetime;
    private String filename;
    private String filepath;
    private BigDecimal latitude; // 위도
    private BigDecimal longitude; // 경도


    public ItemUpdateDto() {
    }

    public ItemUpdateDto(String title, String content, Integer price, int itemTypeId, int saleStatus, Date updateDatetime) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.itemTypeId = itemTypeId;
        this.saleStatus = saleStatus;
        this.updateDatetime = updateDatetime;
    }

    public ItemUpdateDto(String title, String content, Integer price, int itemTypeId, int saleStatus,
                         Date updateDatetime, String filename, String filepath, BigDecimal latitude, BigDecimal longitude) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.itemTypeId = itemTypeId;
        this.saleStatus = saleStatus;
        this.updateDatetime = updateDatetime;
        this.filename = filename;
        this.filepath = filepath;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
