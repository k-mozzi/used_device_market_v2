package teamproject.usedmarket.domain.item;

public enum SaleStatus {
    ON_SALE("판매중",1), SOLD_OUT("판매완료",2);

    private final String description;
    private final int saleStatusId;

    SaleStatus(String description, int saleStatusId) {
        this.description = description;
        this.saleStatusId = saleStatusId;
    }

    public String getDescription() {
        return description;
    }

    public int getSaleStatusId() {
        return saleStatusId;
    }
}
