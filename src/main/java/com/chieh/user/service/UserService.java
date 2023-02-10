package com.chieh.user.service;

import org.springframework.lang.Nullable;

public interface UserService {

    void insert(@Nullable String str);

    void delete();

    void update();

    void select();

}
