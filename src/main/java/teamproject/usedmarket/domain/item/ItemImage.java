package teamproject.usedmarket.domain.item;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * 첨부파일
 */
@Data
public class ItemImage {

    private int itemImageId;
    private Long itemId;
    private String fileName;
    private String filePath;
    private Integer orderingNumber;
    private Date createDatetime;
    private Date updateDatetime;
    private boolean repImageCheck;
    private boolean open;



    public ItemImage() {
    }

}
