package teamproject.usedmarket.domain.item;

public enum ItemType {

    LAPTOP("노트북"), TABLET("태블릿"), PHONE("스마트폰");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
