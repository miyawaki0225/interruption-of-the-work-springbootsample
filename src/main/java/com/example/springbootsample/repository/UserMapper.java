package com.example.springbootsample.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.springbootsample.domain.user.model.MUser;

@Mapper
public interface UserMapper {
    public int insertOne(MUser user);
}
