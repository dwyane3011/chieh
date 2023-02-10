package com.chieh.entity;

import com.chieh.user.entity.model.UserEntity;
import com.chieh.user.security.ChiehUserDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class Request {

    private String requestId;

    private ChiehUserDetails userDetails;

}
