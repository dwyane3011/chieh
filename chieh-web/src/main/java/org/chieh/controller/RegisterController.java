package org.chieh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {
	
	@RequestMapping("/index")
	public String index() {
		logger.info("注册页面");
		return "/register/index";
	}
	
	@ResponseBody
	@RequestMapping("/register")
	public JSONObject register() {
		logger.info("注册成功");
		JSONObject res = new JSONObject();
		res.put("code", 0);
		res.put("msg", "注册成功");
		return res;
	}
}
