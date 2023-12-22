package teamproject.usedmarket.web.item;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class StoreFileName {

    private Long itemId;
    private String itemName;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;
}
