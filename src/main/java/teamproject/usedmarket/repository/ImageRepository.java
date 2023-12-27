package teamproject.usedmarket.repository;

import org.springframework.web.multipart.MultipartFile;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {

    public void save(ItemImage itemImage);


    public List<ItemImage> findByItemId(Long itemId);

    public ItemImage findById(int itemImageId);


    public List<ItemImage> findAll();



    public void delete(int itemImageId);
}
