package org.chieh.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	
	private int id;
	
	@NotEmpty(message="用户名不能为空")
	@Length(max=50, message="用户名最大不超过50个字符")
	private String username;
	
	@NotEmpty(message="密码不能为空")
	@Length(max=50, message="密码最大不超过50个字符")
	private String password;
	//确认密码
	private String confirmPsw;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPsw() {
		return confirmPsw;
	}

	public void setConfirmPsw(String confirmPsw) {
		this.confirmPsw = confirmPsw;
	}
}
