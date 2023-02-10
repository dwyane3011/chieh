package com.chieh.user.entity.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity {
    @Id
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Date createTime;
    private Date updateTime;
}
