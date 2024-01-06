package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.member.Member;
import teamproject.usedmarket.repository.MemberUpdateDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(Member member);

    void update(@Param("memberId") Long memberId, @Param("updateParam") MemberUpdateDto updateParam);

    void delete(Long memberId);

    Optional<Member> findByMemberId(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findAll();

    List<Item> findItemsSortedOnSale(Long memberId);

    List<Item> findItemsSortedSoldOut(Long memberId);

    List<Item> findItemsSorted(Long memberId);

    List<Item> findMembersWithPaging(Map<String, Object> params);

    int countItems();


}
