package teamproject.usedmarket.repository.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.member.Member;
import teamproject.usedmarket.repository.ItemUpdateDto;
import teamproject.usedmarket.repository.MemberRepository;
import teamproject.usedmarket.repository.MemberUpdateDto;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        memberMapper.save(member);
        return member;
    }

    @Override
    public void update(Long memberId, MemberUpdateDto updateParam) {
        memberMapper.update(memberId, updateParam);
    }

    @Override
    public void delete(Long memberId) {
        memberMapper.delete(memberId);
    }


    @Override
    public Optional<Member> findByMemberId(Long memberId) {
        return memberMapper.findByMemberId(memberId);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return memberMapper.findByLoginId(loginId);
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public List<Item> findItemsSortedOnSale(Long memberId) {
        return memberMapper.findItemsSortedOnSale(memberId);
    }

    @Override
    public List<Item> findItemsSortedSoldOut(Long memberId) {
        return memberMapper.findItemsSortedSoldOut(memberId);
    }

    @Override
    public List<Item> findItemsSorted(Long memberId) {
        return memberMapper.findItemsSorted(memberId);
    }


    @Override
    public List<Item> findMembersWithPaging(int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("pageSize", pageSize);
        return memberMapper.findMembersWithPaging(params);
    }

    @Override
    public int countItems() {
        return memberMapper.countItems();
    }


}