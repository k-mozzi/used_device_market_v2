package teamproject.usedmarket.domain.item;

public enum ItemType {

    LAPTOP("노트북", 1),
    TABLET("태블릿", 2),
    PHONE("스마트폰", 3);

    private final String description;
    private final int itemTypeId;

    ItemType(String description, int itemTypeId) {
        this.description = description;
        this.itemTypeId = itemTypeId;
    }

    public String getDescription() {
        return description;
    }

    public int getItemTypeId() {
        return itemTypeId;
    }

}
