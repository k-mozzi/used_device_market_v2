package teamproject.usedmarket.domain.item;

public enum SaleStatus {

    ON_SALE("판매중"), SOLD_OUT("판매완료");

    private final String description;

    SaleStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
