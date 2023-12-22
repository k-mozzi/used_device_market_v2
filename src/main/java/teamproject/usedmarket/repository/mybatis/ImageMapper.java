package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ImageMapper {

    void save(ItemImage imageMapper);

    void update(@Param("itemId") Long itemId, @Param("updateParam") ItemUpdateDto updateParam);

    void incrementViewsCount(Long itemId);

    List<ItemImage> findAll();

    Optional<Item> findById(Long id);
    Optional<ItemImage> findByItemId(Long itemId);

    void delete(Long itemId);
}
