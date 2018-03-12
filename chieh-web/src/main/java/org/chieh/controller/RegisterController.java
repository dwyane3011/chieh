package org.chieh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController {
	
	@RequestMapping("/index")
	public String index() {
		logger.info("×¢²áÒ³Ãæ");
		return "/register/index";
	}
}
