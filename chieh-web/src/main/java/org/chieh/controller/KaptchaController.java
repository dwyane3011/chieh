package org.chieh.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
public class KaptchaController {

	@Autowired
	private Producer kaptchaProducer;

	@RequestMapping("/kaptcha")
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		String capText = kaptchaProducer.createText();
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		BufferedImage bi = kaptchaProducer.createImage(capText);
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
		} catch (IOException e) {
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
