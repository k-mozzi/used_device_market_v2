package teamproject.usedmarket.repository.jdbctemplate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemRepository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
@Repository
public class JdbcTemplateItemRepository implements ItemRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;
    public JdbcTemplateItemRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("item")
                .usingGeneratedKeyColumns("item_id");
//                .usingColumns("item_name", "price", "quantity"); 생략가능
    }

    @Override
    public Item save(Item item) {
        item.setRegiDate(new Date());
        String sql = "insert into item (item_name, price, seller, regi_date) values (:itemName,:price,:seller,:regiDate)";


        SqlParameterSource param = new BeanPropertySqlParameterSource(item);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);

        long key = keyHolder.getKey().longValue();
        item.setItemId(key);
        return item;
    }

    @Override
    public void update(Long itemId, Item updateParam) {
        updateParam.setRegiDate(new Date());
        String sql = "update item set item_name=:itemName, price=:price, seller=:seller where item_id=:itemId";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("itemName", updateParam.getItemName())
                .addValue("price", updateParam.getPrice())
                .addValue("seller", updateParam.getSeller())
                .addValue("itemId", itemId);  //이 부분이 별도로 필요하다.

        template.update(sql, param);
    }

    @Override
    public Optional<Item> findByItemId(Long itemId) {
        String sql = "select item_id, item_name, price, seller, regi_date from item where item_id = :itemId";
        try {
            Map<String, Object> param = Map.of("itemId", itemId);
            Item item = template.queryForObject(sql, param,itemRowMapper());
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Item> findAll() {
        String sql = "select item_id, item_name, price, seller, regi_date from item";

        log.info("sql={}", sql);
        return template.query(sql, itemRowMapper());
    }

    @Override
    public void delete(Long itemID) {
        String sql = "delete from item where item_id = "+itemID;

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("item_id", itemID);

        template.update(sql, param);

    }

    private RowMapper<Item> itemRowMapper() {
        return BeanPropertyRowMapper.newInstance(Item.class); //camel 변환 지원
    }
}