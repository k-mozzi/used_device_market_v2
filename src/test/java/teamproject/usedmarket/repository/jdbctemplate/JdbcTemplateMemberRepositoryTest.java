package teamproject.usedmarket.repository.jdbctemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import teamproject.usedmarket.domain.member.Member;
import teamproject.usedmarket.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class JdbcTemplateMemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Member member1 = new Member("testID", "tester", "123456");
        Member savedMember = memberRepository.save(member1);
        assertThat(savedMember.getLoginId()).isEqualTo("testID");
    }

    @Test
    void findByMemberId() {
        Member member = new Member("testID", "tester", "123456");
        Member savedMember = memberRepository.save(member);
        Long findMemberId = savedMember.getMemberId();

        assertThat(memberRepository.findByMemberId(findMemberId).get()).isEqualTo(member);
    }

    @Test
    void findByLoginId() {
        Member member = new Member("testID", "tester", "123456");
        Member savedMember = memberRepository.save(member);
        String findLoginId = savedMember.getLoginId();

        assertThat(memberRepository.findByLoginId(findLoginId).get()).isEqualTo(member);
    }

    @Test
    void findAll() {
        Member member1 = new Member("testID1", "tester", "123456");
        Member member2 = new Member("testID2", "tester", "123456");
        Member member3 = new Member("testID3", "tester", "123456");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<Member> findList = memberRepository.findAll();
        log.info("findList={}", findList);
        assertThat(findList.size()).isEqualTo(4);
    }
}