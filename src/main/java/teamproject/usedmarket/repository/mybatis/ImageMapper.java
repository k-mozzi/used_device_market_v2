package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ImageMapper {

    void save(ItemImage itemImage);



    List<ItemImage> findAll();

   ItemImage findById(int itemImageId);
    List<ItemImage> findByItemId(@Param("itemId") Long itemId);

    void delete(@Param("itemImageId") int itemImageId);
}
