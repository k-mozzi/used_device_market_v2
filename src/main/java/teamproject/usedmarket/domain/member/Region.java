package teamproject.usedmarket.domain.member;

public enum Region {

    SEOUL("서울", 1),
    INCHEON("인천", 2),
    SEJONG("세종", 3),
    DAEJEON("대전", 4),
    DAEGU("대구", 5),
    GWANGJU("광주", 6),
    ULSAN("울산", 7),
    BUSAN("부산", 8),
    JEJU("제주", 9);

    private final String description;
    private final int regionId;

    Region(String description, int regionId) {
        this.description = description;
        this.regionId = regionId;
    }

    public String getDescription() {
        return description;
    }

    public int getRegionId() {
        return regionId;
    }

    /**
     * 회원 상세정보 창에서 지역 불러올 때 사용
     */
    public static Region findRegionByRegionId(int regionId) {
        for (Region region : Region.values()) {
            if (region.getRegionId() == regionId) {
                return region;
            }
        }
        throw new IllegalArgumentException("Invalid regionId: " + regionId);
    }

}