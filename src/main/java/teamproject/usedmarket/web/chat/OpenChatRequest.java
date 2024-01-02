package teamproject.usedmarket.web.chat;

import lombok.Data;

@Data
public class OpenChatRequest {
    private String seller;

    // 생성자, 게터, 세터 등 필요한 메서드 추가

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
