package teamproject.usedmarket.domain.item;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Item {

    private Long itemId;
    private int itemTypeId;
    private String title;
    private String content;
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


    private UploadFile attachFile;
    private List<UploadFile> imageFiles;




    public Item(int itemTypeId, String title, String content, Integer price, int saleStatus,
                Long sellerMemberId, Long buyerMemberId, int viewsCount, String repImagePath,
                Date createDatetime, Date updateDatetime) {
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
    }



}