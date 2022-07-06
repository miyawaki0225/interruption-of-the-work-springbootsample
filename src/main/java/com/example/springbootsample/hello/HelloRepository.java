package com.example.springbootsample.hello;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {
    private JdbcTemplate jdbcTemplate;
    
    public Map<String, Object>findById(String id){
        String query = "SELECT * FROM employee WHERE id = ?";
        Map<String,Object> employee = jdbcTemplate.queryForMap(query,id);
        return employee;
    }
}
