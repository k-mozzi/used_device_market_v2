package teamproject.usedmarket.domain.item;

import lombok.Data;

import java.util.Date;

/**
 * 첨부파일
 */
@Data
public class ItemImage {

    private int ItemImageId;
    private Long itemId;
    private String filePath;
    private Integer orderingNumber;
    private Date createDatetime;
    private Date updateDatetime;



    public ItemImage() {
    }

}
