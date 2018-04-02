package org.chieh.controller;

import org.chieh.bean.User;
import org.chieh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index() {
		return "/register/index";
	}
	
	@ResponseBody
	@RequestMapping("/register")
	public JSONObject register(@Validated User user, BindingResult bindingResult) {
		JSONObject res = new JSONObject();
		if(bindingResult.hasErrors()) {
			res.put("code", 1);
			res.put("msg", bindingResult.getAllErrors());
		} else {
			userService.register(user);
			res.put("code", 0);
			res.put("msg", "×¢²á³É¹¦");
		}
		return res;
	}
}
