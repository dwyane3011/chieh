package org.chieh.service.impl;

import org.chieh.bean.User;
import org.chieh.dao.UserDao;
import org.chieh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	public void register(User user) {
		userDao.register(user);
	}
}
