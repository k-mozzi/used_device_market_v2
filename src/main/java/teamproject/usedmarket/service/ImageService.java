package teamproject.usedmarket.service;

import org.springframework.web.multipart.MultipartFile;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImageService {
    ItemImage save(Long itemId, MultipartFile imageFiles) throws IOException;

    void update(Long itemId, ItemUpdateDto updateParam, MultipartFile file) throws IOException;

    Optional<ItemImage> findById(Long id);

    List<ItemImage> findImages();

//    void delete(Long itemId);
}
