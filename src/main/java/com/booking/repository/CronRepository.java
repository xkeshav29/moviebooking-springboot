package com.booking.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class CronRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CronRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final Logger log = LoggerFactory.getLogger(CronRepository.class);

    public int updateCron(String cronName, Timestamp timestamp) {
        try {
            log.info("updating cron {}", cronName);
            MapSqlParameterSource paramMap = new MapSqlParameterSource();
            paramMap.addValue("cron_name", cronName);
            paramMap.addValue("last_updated_timestamp", timestamp);
            return jdbcTemplate.update(
                    "UPDATE cron SET last_execution_time = :last_updated_timestamp WHERE cron_name = :cron_name" +
                            " AND last_execution_time < date_format(:last_updated_timestamp, '%Y-%m-%d %H:%i:00')",
                    paramMap);
        } catch(Exception ex) {
            log.error("Error while updating cron name {}, timestamp {} ", cronName, timestamp, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}
