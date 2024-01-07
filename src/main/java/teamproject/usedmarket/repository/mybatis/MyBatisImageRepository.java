package teamproject.usedmarket.repository.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ImageRepository;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.ItemUpdateDto;
import teamproject.usedmarket.web.itemimage.ImageUpdateDto;

import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisImageRepository implements ImageRepository {
    private final ImageMapper imageMapper;

    @Override
    public void save(ItemImage itemImage) {
        imageMapper.save(itemImage);
    }

    @Override
    public List<ItemImage> findByItemId(Long itemId) {
        return imageMapper.findByItemId(itemId);
    }

    @Override
    public ItemImage findById(int itemImageId) {
        return imageMapper.findById(itemImageId);
    }

    @Override
    public List<ItemImage> findAll() {
        return imageMapper.findAll();
    }

    @Override
    public void update(int itemImageId, ImageUpdateDto updateDto) {
        imageMapper.update(itemImageId, updateDto);
    }

    @Override
    public void delete(int itemImageId) {
        imageMapper.delete(itemImageId);
    }
}