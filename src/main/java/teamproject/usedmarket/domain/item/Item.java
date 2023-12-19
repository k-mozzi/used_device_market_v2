package teamproject.usedmarket.domain.item;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
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



    public Item() {
    }


}
