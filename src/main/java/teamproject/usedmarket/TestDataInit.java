package teamproject.usedmarket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemStatus;
import teamproject.usedmarket.domain.item.ItemType;
import teamproject.usedmarket.domain.member.Member;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.MemberRepository;

import javax.annotation.PostConstruct;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 확인용 초기 데이터 추가
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init");
        if(memberRepository.findByLoginId("test").isEmpty()) {
            memberRepository.save(new Member("test", "tester", "test!"));
        }
        if (itemRepository.findAll().isEmpty()) {
            itemRepository.save(new Item("갤럭시s23", 1000000, "홍길동", new Date()));
            itemRepository.save(new Item("아이폰15", 1300000, "고길동", new Date()));
        }
    }


    /**
     * 테스트용 데이터 추가
     */
//    @PostConstruct
//    public void init() {
//
//        if(memberRepository.findByLoginId("test").isEmpty()) {
//            memberRepository.save(new Member("test", "tester", "test!"));
//        }
//
//        itemRepository.save(new Item("갤럭시s23", 1000000, "홍길동", new Date()));
//        itemRepository.save(new Item("아이폰15", 1300000, "고길동", new Date()));
//
//    }

}