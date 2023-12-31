package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.mybatis.PagingMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityServiceV1 {


    private final PagingMapper pagingMapper;

    public List<Item> getEntitiesWithPaging(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return pagingMapper.getEntitiesWithPaging(offset, pageSize);
    }

    public int countTotalEntities() {
        return pagingMapper.countTotalEntities();
    }
}
