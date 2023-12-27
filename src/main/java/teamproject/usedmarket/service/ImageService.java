package teamproject.usedmarket.service;

import org.springframework.web.multipart.MultipartFile;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImageService {

    List<ItemImage> findByItemId(Long itemid);
    void save(Long itemId, List<MultipartFile> imageFiles) throws IOException;


    ItemImage findById(int itemImageId);

    List<ItemImage> findImages();

    void delete(int itemImageId);


}
