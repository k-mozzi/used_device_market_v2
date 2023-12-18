package teamproject.usedmarket.repository;

import lombok.Data;

@Data
public class ItemUpdateDto {

    private String itemName;
    private Integer price;
    private String seller;

    public ItemUpdateDto() {
    }

    public ItemUpdateDto(String itemName, Integer price, String seller) {
        this.itemName = itemName;
        this.price = price;
        this.seller = seller;
    }
}
