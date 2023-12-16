package teamproject.usedmarket.repository.jdbctemplate;

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
import teamproject.usedmarket.domain.member.Member;
import teamproject.usedmarket.repository.MemberRepository;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("member")
                .usingGeneratedKeyColumns("member_id");
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member (login_id, member_name, password) values (:loginId,:memberName,:password)";


        SqlParameterSource param = new BeanPropertySqlParameterSource(member);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(sql, param, keyHolder);

        long key = keyHolder.getKey().longValue();
        member.setMemberId(key);
        return member;
    }

    @Override
    public Optional<Member> findByMemberId(Long memberId) {
        String sql = "select member_id, member_name, login_id as loginId, password from member where member_id = :memberId";
        try {
            Map<String, Object> param = Map.of("memberId", memberId);
            Member member = template.queryForObject(sql, param, memberRowMapper());
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<Member> findByLoginId(String loginId) {
        String sql = "select member_id, member_name, login_id as loginId, password from member where login_id = :loginId";
        try {
            Map<String, Object> param = Map.of("loginId", loginId);
            Member member = template.queryForObject(sql, param, memberRowMapper());
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public List<Member> findAll() {
        String sql = "select member_id, member_name, login_id as loginId, password from member";
        return template.query(sql, memberRowMapper());
    }


    private RowMapper<Member> memberRowMapper() {
        return BeanPropertyRowMapper.newInstance(Member.class); //camel 변환 지원
    }
}