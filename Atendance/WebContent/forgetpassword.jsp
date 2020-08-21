<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forget Password</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>

	<%
		if (session.getAttribute("error") != null) {
	%>
	<p style="color: red">Invalid E-mail</p>
	<%
		session.removeAttribute("error");
	} else if (session.getAttribute("wrong") != null) {
	%>
	<p style="color: red">Email is invalid</p>
	<%
		session.removeAttribute("wrong");
	}
	%>
	<div class="loginbox">
		<img src="img/expertlogo.png" class="user">
		<form action="<%=request.getContextPath()%>/Logincon" method="post">
			<input type="text" name="email" placeholder="enter your email"
				required> <input type="hidden" name="flag"
				value="forgetpassword"> <input type="submit" value="Submit"
				name="submit">
		</form>
		<div>
			<a href="Com_Login.jsp"> Login </a>
		</div>
	</div>

</body>
</html>