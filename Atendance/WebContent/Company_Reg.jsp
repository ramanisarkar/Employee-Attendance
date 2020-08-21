<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("wrong") != null) {
	%>
	<p style="color: red">Email is invalid</p>
	<%
		session.removeAttribute("wrong");
	}
	%>
	<div class="loginbox">
		<img src="img/expertlogo.png" class="user">
		<form action="Company" method="post">
			<input type="text" name="companyname" placeholder="company name"
				required> <input type="email" name="email"
				placeholder="company email" required> <input type="password"
				name="pass" placeholder="password" required> <input
				type="text" name="companyaddress" placeholder="Address" required>
			<input type="hidden" name="flag" value="insert" /><br /> <br /> <input
				type="submit" value="Submit" name="submit">
		</form>
		<a href="Com_Login.jsp">Login</a>
	</div>

</body>
</html>