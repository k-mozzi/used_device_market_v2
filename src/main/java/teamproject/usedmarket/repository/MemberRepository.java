package teamproject.usedmarket.repository;

import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    void update(Long memberId, MemberUpdateDto updateParam);

    Optional<Member> findByMemberId(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findAll();

    List<Item> findItemsSortedOnSale(Long memberId);

    List<Item> findItemsSortedSoldOut(Long memberId);

    List<Item> findItemsSorted(Long memberId);

    List<Item> findMembersWithPaging(int page, int pageSize);

    int countItems();

}
