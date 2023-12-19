package teamproject.usedmarket.config;



import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.MemberRepository;
import teamproject.usedmarket.repository.jdbctemplate.JdbcTemplateItemRepository;
import teamproject.usedmarket.repository.jdbctemplate.JdbcTemplateMemberRepository;
import teamproject.usedmarket.service.ItemService;
import teamproject.usedmarket.service.ItemServiceV1;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateConfig {

    private final DataSource dataSource;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JdbcTemplateItemRepository(dataSource);
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }

}

