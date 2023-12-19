//package teamproject.usedmarket.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor  // 또는  @AllArgsConstructor
//public class MemberServiceImpl {
//
//
//    private final MemberRepository memberRepository;
//
//
////    @Autowired
////    public MemberService(MemberRepository memberRepository) {           <--@RequiredArgsConstructor 가대신해줌
////        this.memberRepository = memberRepository;
////    }
//
//
//    //회원 가입
//    @Transactional // default  -> readOnly = false
//    public Long join(Member member) {
//        validateDuplicateMember(member); //중복 회원 검증
//        memberRepository.save(member);
//        return member.getId();
//    }
//
//    private void validateDuplicateMember(Member member) {
//
//        List<Member> findMembers = memberRepository.findByName(member.getName());
//        if (!findMembers.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }
//    }
//
//    //회원 전체 조회
//    public List<Member> findMembers() {
//        return memberRepository.findAll();
//    }
//
//    public Member findOne(Long memberId) {
//        return memberRepository.findOne(memberId);
//    }
//
//
//}
