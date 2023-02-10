package com.chieh.user.entity.request;

import com.chieh.entity.Request;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User extends Request {

    private Integer id;

    private String mobile;

    private String nickName;

    private String username;

    private String password;

}
