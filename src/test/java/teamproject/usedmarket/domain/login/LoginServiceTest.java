//package teamproject.usedmarket.domain.login;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import teamproject.usedmarket.domain.member.Member;
//import teamproject.usedmarket.repository.MemoryMemberRepository;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Slf4j
//class LoginServiceTest {
//
//    MemoryMemberRepository memberRepository;
//    LoginService loginService;
//
//    @BeforeEach
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        loginService = new LoginService(memberRepository);
//    }
//
//    @AfterEach
//    public void afterEach() {
//        memberRepository.clearStore();
//    }
//
//    @Test
//    void 회원가입() throws Exception {
//
//        //given
//        Member member = new Member();
//        member.setMemberName("hello");
//        member.setLoginId("loginId");
//        member.setPassword("1234");
//        //when
//        String saveId = loginService.join(member);
//        //then
//        Optional<Member> findMember = memberRepository.findByLoginId("loginId");
//        assertThat(member.getMemberName()).isEqualTo(findMember.get().getMemberName());
//
//    }
//
//    @Test
//    void 중복_회원_예외() {
//        //given
//        Member member1 = new Member();
//        member1.setMemberName("AAA");
//        member1.setLoginId("loginId");
//        member1.setPassword("5678");
//
//        Member member2 = new Member();
//        member2.setMemberName("BBB");
//        member2.setLoginId("loginId");
//        member2.setPassword("1234");
//        //when
//
//        loginService.join(member1);
//        IllegalStateException e = assertThrows(IllegalStateException.class,
//                () -> loginService.join(member2));
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//
//    }
//
//    @Test
//    void findMembers() {
//    }
//
//    @Test
//    void findOne() {
//    }
//}