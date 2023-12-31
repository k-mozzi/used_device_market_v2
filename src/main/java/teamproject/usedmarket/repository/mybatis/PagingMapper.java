package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import teamproject.usedmarket.domain.item.Item;

import java.util.List;

@Mapper
public interface PagingMapper {
    List<Item> getEntitiesWithPaging(@Param("offset") int offset, @Param("limit") int limit);

    int countTotalEntities();
}
