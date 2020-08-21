<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update password</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<%-- <form action="<%=request.getContextPath()%>/Login_controler" method="post">
Password:-
<input type="password" name="password" ><br><br>
Confirm_password:-
	<input type="password" name="cpassword"><br><br>
<input type="hidden" name="flag" value="verify"/>
<input type="submit" value="SUBMIT"/><br/><br/>
</form> --%>
	<%
		if (session.getAttribute("error") != null) {
	%>
	<p style="color: red">Password Mismatch</p>
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
			<input type="password" name="password" placeholder="password"
				required> <input type="password" name="cpassword"
				placeholder="confirm password" required> <input
				type="hidden" name="flag" value="updatepassword"> <input
				type="submit" value="Submit" name="submit">
		</form>
		<a href="Com_Login.jsp"> Login </a>
	</div>
</body>
</html>