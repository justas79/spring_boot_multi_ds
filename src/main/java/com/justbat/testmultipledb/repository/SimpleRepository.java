package com.justbat.testmultipledb.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SimpleRepository {

    private final JdbcTemplate jdbcTemplate;

    public String returnString() {
        List<String> result = jdbcTemplate.query("select name from temp", new Object[]{},
                (rs, rowNum) -> rs.getString(1)
        );
        return result.iterator().next();
    }
}
