package teamproject.usedmarket.domain.item;

import lombok.Data;

import java.util.Date;

@Data
public class Item {

    private Long itemId;
    private String itemName;
    private Integer price;
    private ItemType category;
    private String seller;
    private ItemStatus status;
    private Date regiDate;


    public Item() {
    }

    public Item(String itemName, Integer price, ItemType category, String seller, ItemStatus status, Date regiDate) {
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.seller = seller;
        this.status = status;
        this.regiDate = regiDate;
    }

    public Item(String itemName, Integer price, String seller, Date regiDate) {
        this.itemName = itemName;
        this.price = price;
        this.seller = seller;
        this.regiDate = regiDate;
    }
}
