package teamproject.usedmarket.domain.item;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Item {

    private Long itemId;
    private String itemName;
    private List<ItemType> itemTypes = new ArrayList<>();
    private String content;
    private Integer price;
    private String seller;
    private String buyer;
    private Integer views;
    private String imageFilename;
    private String imageFilepath;
    private SaleStatus status;
    private Date regiDate;
    private Date updateDate;


    public Item() {
    }

    public Item(String itemName, String content, Integer price, String seller, String buyer, Integer views, String imageFilename, String imageFilepath, SaleStatus status, Date regiDate, Date updateDate) {
        this.itemName = itemName;
        this.content = content;
        this.price = price;
        this.seller = seller;
        this.buyer = buyer;
        this.views = views;
        this.imageFilename = imageFilename;
        this.imageFilepath = imageFilepath;
        this.status = status;
        this.regiDate = regiDate;
        this.updateDate = updateDate;
    }
}
