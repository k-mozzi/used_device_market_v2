package teamproject.usedmarket.repository.mybatis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void save(Item item);

//    void update(@Param("itemId") Long itemId, @Param("updateParam") ItemUpdateDto updateParam);

    List<Item> findAll();

    Optional<Item> findById(Long id);


}