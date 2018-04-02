<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>欢迎━(*｀∀´*)ノ亻!注册</title>
</head>
<body>
<h2>用户注册</h2>
<form action="${pageContext.request.contextPath }/register/register" method="post">
	<table>
		<tr>
			<td style="text-align:right">用户名：</td>
			<td><input type="text" name="username"></td>
		</tr>
		<tr>
			<td style="text-align:right">密码：</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td style="text-align:right">确认密码：</td>
			<td><input type="password" name="confirmpsw"></td>
		</tr>
		<tr>
			<td><input type="submit" value="立即注册"></td>
		</tr>
	</table>
</form>
</body>
</html>