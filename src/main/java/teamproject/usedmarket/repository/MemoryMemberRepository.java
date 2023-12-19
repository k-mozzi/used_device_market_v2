//package teamproject.usedmarket.repository;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Repository;
//import teamproject.usedmarket.domain.member.Member;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Slf4j
//@Repository
//public class MemoryMemberRepository implements MemberRepository {
//
//    private static Map<Long, Member> store = new ConcurrentHashMap<>();
//    private static long sequence = 0L;
//
//    @Override
//    public Member save(Member member) {
//        member.setMemberId(++sequence);
//        log.info("save: member={}", member);
//        store.put(member.getMemberId(), member);
//        return member;
//    }
//
//    @Override
//    public Optional<Member> findByMemberId(Long memberId) {
//        return store.get(memberId);
//    }
//
//    @Override
//    public Optional<Member> findByLoginId(String loginId) {
//        return findAll().stream()
//                .filter(m -> m.getLoginId().equals(loginId))
//                .findFirst();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    public void clearStore() {
//        store.clear();
//    }
//}
