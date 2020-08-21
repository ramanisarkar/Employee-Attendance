<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<%
		if (session.getAttribute("loginResult") != null) {
	%>
	<p style="color: red">Invalid email and password</p>
	<%
		session.removeAttribute("loginResult");
	out.print("jiji");
	} else if (session.getAttribute("wrong") != null) {
	%>
	<p style="color: red">Email is invalid</p>
	<%
		session.removeAttribute("wrong");
	}
	%>
	<div class="loginbox">
		<img src="img/expertlogo.png" class="user">
		<form action="Logincon" method="post">
			<input type="text" name="email" placeholder="Username" required>
			<input type="password" name="pwd" id="password"
				placeholder="Password" required>
			<p>
				Remember me <input type="checkbox" value="remind me"
					name="remind me">
			</p>
			<input type="hidden" name="flag" value="verify" /> <input
				type="submit" value="Sign In" name="submit">
		</form>
		<a href="forgetpassword.jsp"> Forget your Password? </a>
	</div>
</body>
</html>