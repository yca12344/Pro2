package com.example.springdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class SpringdbApplicationTests {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() throws SQLException {
        System.out.println("默认数据源为："+dataSource.getClass());
        System.out.println("数据库连接实例："+dataSource.getConnection());
        Integer i = jdbcTemplate.queryForObject("select count(*) from `user`",Integer.class);
        System.out.println("person表中共有"+i+"条数据");
    }

}
