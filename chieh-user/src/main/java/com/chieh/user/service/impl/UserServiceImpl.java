package com.chieh.user.service.impl;

import com.chieh.user.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public void insert(String str) {
        System.out.println("添加");
    }

    @Override
    public void delete() {
        System.out.println("删除");
    }

    @Override
    public void update() {
        System.out.println("修改");
    }

    @Override
    public void select() {
        System.out.println("查询");
    }
}
