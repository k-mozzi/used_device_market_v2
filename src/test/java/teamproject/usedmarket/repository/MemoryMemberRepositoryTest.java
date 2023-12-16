//package teamproject.usedmarket.repository;
//
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import teamproject.usedmarket.domain.member.Member;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Slf4j
//class MemoryMemberRepositoryTest {
//
//    MemoryMemberRepository repository = new MemoryMemberRepository();
//
//    @BeforeEach
//    void afterEach() {
//        repository.clearStore();
//    }
//
//
//
//    @Test
//    void save() {
//
//        Member member = new Member();
//        member.setLoginId("test1");
//
//        Member savedMember = repository.save(member);
//        assertThat(savedMember.getLoginId()).isEqualTo("test1");
//    }
//
//    /*@Test
//    void findByMemberId() {
//        Member member = new Member();
//        Member savedMember = repository.save(member);
//        assertThat(savedMember.getMemberId()).isEqualTo(1);
//    }*/
//
//    @Test
//    void findByLoginId() {
//        Member member = new Member();
//        member.setLoginId("test1");
//        Member savedMember = repository.save(member);
//        assertThat(savedMember.getLoginId()).isEqualTo("test1");
//    }
//
//    @Test
//    void findAll() {
//        Member member1 = new Member();
//        Member member2 = new Member();
//        Member member3 = new Member();
//
//        repository.save(member1);
//        repository.save(member2);
//        repository.save(member3);
//
//        List<Member> result = repository.findAll();
//
//        assertThat(result.size()).isEqualTo(3);
//    }
//}