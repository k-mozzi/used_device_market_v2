package teamproject.usedmarket.domain.item;

import lombok.Data;

import java.util.Date;

/**
 * 첨부파일
 */
@Data
public class ItemImage {

    private Long ItemImageId;
    private Item item;
    private String filepath;
    private Integer orderingNumber;
    private Date regiDate;
    private Date updateDate;

    public ItemImage() {
    }

    public ItemImage(Item item, String filepath, Integer orderingNumber, Date regiDate, Date updateDate) {
        this.item = item;
        this.filepath = filepath;
        this.orderingNumber = orderingNumber;
        this.regiDate = regiDate;
        this.updateDate = updateDate;
    }
}
