package teamproject.usedmarket.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.MemberRepository;
import teamproject.usedmarket.repository.mybatis.ItemMapper;
import teamproject.usedmarket.repository.mybatis.MemberMapper;
import teamproject.usedmarket.repository.mybatis.MyBatisItemRepository;
import teamproject.usedmarket.repository.mybatis.MyBatisMemberRepository;
import teamproject.usedmarket.service.ItemService;
import teamproject.usedmarket.service.ItemServiceV1;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {
    private final ItemMapper itemMapper;
    private final MemberMapper memberMapper;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new MyBatisItemRepository(itemMapper);
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MyBatisMemberRepository(memberMapper);
    }

}