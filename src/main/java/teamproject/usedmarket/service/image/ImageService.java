package teamproject.usedmarket.service.image;

import org.springframework.web.multipart.MultipartFile;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.web.itemimage.ImageUpdateDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImageService {
    List<ItemImage> findByItemId(Long itemid);

    void save(Long itemId, List<MultipartFile> imageFiles) throws IOException;

    ItemImage findById(int itemImageId);

    List<ItemImage> findImages();

    void delete(int itemImageId);

    void update(int itemImageId, ImageUpdateDto updateDto) throws IOException;
}
