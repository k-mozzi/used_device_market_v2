package teamproject.usedmarket.domain.item;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ItemType {
    private int itemTypeId;
    private String itemTypeName;
    private Date createDatetime;
    private Date updateDatetime;


    public ItemType() {
    }


}
