package teamproject.usedmarket.repository;

import lombok.Data;

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
}
