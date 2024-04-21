package com.example.envers.envers.repository;

import com.example.envers.envers.SearchUserEventHistoryCondition;
import com.example.envers.envers.UserEventHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserEventHistoryRepository {

    private final NamedParameterJdbcTemplate template;

    public UserEventHistoryRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    public Page<UserEventHistory> findAllByCondition(Pageable pageable, SearchUserEventHistoryCondition condition) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(condition);
        String totalSql = "select count(1) " +
                "   from revision r " +
                "   join users_aud ua on r.rev = ua.rev ";

        int total = template.queryForObject(totalSql, param, Integer.class);

        String contentSql = "select r.rev, r.modified_by, r.modified_at, r.authorities, r.group_name, " +
                "       ua.username, ua.revtype, ua.password, ua.password_mod, ua.name, ua.name_mod, " +
                "       ua.phone_number, ua.phone_number_mod, ua.email, ua.email_mod, ua.confirm_yn, ua.confirm_yn_mod, " +
                "       ua.renew_password, ua.renew_password_mod" +
                "   from revision r " +
                "   join users_aud ua on r.rev = ua.rev " +
                "   limit :limit offset :offset";

        List<UserEventHistory> content = template.query(contentSql, param, userEventHistoryRowMapper());

        return new PageImpl<>(content, pageable, total);
    }

    private RowMapper<UserEventHistory> userEventHistoryRowMapper() {
        return BeanPropertyRowMapper.newInstance(UserEventHistory.class);
    }
}
