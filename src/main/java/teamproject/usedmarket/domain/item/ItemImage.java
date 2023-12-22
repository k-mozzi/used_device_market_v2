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

//    private Item item;
    private Long itemId;
    private List<String> fileNames;
    private String fileName1;
    private String fileName2;
    private String fileName3;
    private String fileName4;
    private String fileName5;
    private String filePath;
    private Integer orderingNumber;
    private List<MultipartFile> imageFiles;
    private Date createDatetime;
    private Date updateDatetime;



    public ItemImage() {
    }

}
