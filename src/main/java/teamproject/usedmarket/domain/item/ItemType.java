package teamproject.usedmarket.domain.item;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ItemType {
    private Long itemTypeId;
    private List<Item> items = new ArrayList<>();
    private String itemTypeName;
    private Date regiDate;
    private Date updateDate;

    public ItemType() {
    }

    public ItemType(String itemTypeName, Date regiDate, Date updateDate) {
        this.itemTypeName = itemTypeName;
        this.regiDate = regiDate;
        this.updateDate = updateDate;
    }
}
