package com.example.envers.envers.repository;

import com.example.envers.envers.SearchUserEventHistoryCondition;
import com.example.envers.envers.UserEventHistory;
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

    public List<UserEventHistory> findUserEventHistory(SearchUserEventHistoryCondition condition) {
        String sql = "select r.rev, r.modified_by, r.modified_at, " +
                "       ua.username, ua.revtype, ua.password, ua.password_mod, ua.name, ua.name_mod, " +
                "       ua.phone_number, ua.phone_number_mod, ua.email, ua.email_mod, ua.confirm_yn, ua.confirm_yn_mod, " +
                "       ua.renew_password, ua.renew_password_mod" +
                "   from revision r " +
                "   join users_aud ua on r.rev = ua.rev";

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(condition);
        return template.query(sql, param, userEventHistoryRowMapper());
    }

    private RowMapper<UserEventHistory> userEventHistoryRowMapper() {
        return BeanPropertyRowMapper.newInstance(UserEventHistory.class);
    }
}
