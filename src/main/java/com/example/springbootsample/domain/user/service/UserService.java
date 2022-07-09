package com.example.springbootsample.domain.user.service;

import org.apache.ibatis.annotations.Mapper;

import com.example.springbootsample.domain.user.model.MUser;

@Mapper
public interface UserService {
    public void signup(MUser user);
}
