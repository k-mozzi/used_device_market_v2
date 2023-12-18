package teamproject.usedmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import teamproject.usedmarket.config.MyBatisConfig;


@Import(MyBatisConfig.class)
@SpringBootApplication
public class UsedmarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsedmarketApplication.class, args);
    }

//    @Bean
//    @Profile("local")
//    public TestDataInit testDataInit(MemberRepository memberRepository, ItemRepository itemRepository) {
//        return new TestDataInit(memberRepository, itemRepository);
//    }
}
