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
    public List<ItemImage> findAll() {
        return imageMapper.findAll();
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        imageMapper.update(itemId, updateParam);
        log.info("update item={}", updateParam.getUpdateDatetime());
    }
    @Override
    public void incrementViewsCount(Long itemId) {
        imageMapper.incrementViewsCount(itemId);
    }

    @Override
    public void delete(int itemImageId) {
        imageMapper.delete(itemImageId);
    }






}