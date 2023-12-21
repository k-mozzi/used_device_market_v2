package teamproject.usedmarket.service;

import org.springframework.web.multipart.MultipartFile;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    void save(Item item, MultipartFile imageFiles) throws IOException;

    void update(Long itemId, ItemUpdateDto updateParam, MultipartFile file) throws IOException;

    Optional<Item> findById(Long id);

    List<Item> findItems();

    void delete(Long itemId);
}