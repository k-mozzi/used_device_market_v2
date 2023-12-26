package teamproject.usedmarket.repository;

import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findByMemberId(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findAll();

    List<Item> findSellItemByMemberId(Long memberId);

}
