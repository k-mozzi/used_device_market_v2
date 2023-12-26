package teamproject.usedmarket.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.member.Member;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    void save(Member member);

    Optional<Member> findByMemberId(Long memberId);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findAll();

    List<Item> findSellItemByMemberId(Long memberId);


}
