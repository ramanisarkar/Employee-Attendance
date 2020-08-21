<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
body {
	margin: 0;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 25%;
	background-color: #f1f1f1;
	position: fixed;
	height: 100%;
	overflow: auto;
}

li a {
	display: block;
	color: #000;
	padding: 8px 16px;
	text-decoration: none;
}

li a.active {
	background-color: #4CAF50;
	color: white;
}

li a:hover:not(.active) {
	background-color: #555;
	color: white;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div>
		<ul>
			<c:forEach items="${sessionScope.data }" var="q">
				<li><a href="Company_Login.jsp">Home</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Department?flag=insert&id=${q.id }">Add
						Department</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Employee?flag=insert&id=${q.id }&flag2=company">Add
						Employee</a></li>
				<%
					if (session.getAttribute("empmessage") != null) {
				%>
				<li><a
					href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=hr&flag3=company&id=${q.id }">View
						Notification <span style="color: red;"> &#9679 </span>
				</a></li>
				<%
					} else if (session.getAttribute("empmessage") == null) {
				%>
				<li><a
					href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=hr&flag3=company&id=${q.id }">View
						Notification</a></li>
				<%
					}
				%>
				<li><a
					href="<%=request.getContextPath()%>/Department?flag=departmentsearch&id=${q.id }">View
						Department</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Employee?flag=companysearch&flag2=company&id=${q.id }">View
						Employee</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Attendance?flag=companyemployeepresent&flag2=company&id=${q.id }">Present
						Employee</a></li>
				<li><a href="Com_Login.jsp">Logout</a></li>
			</c:forEach>
		</ul>
	</div>
	<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
		<div style="padding-top: 2%;">
			<%
				if (session.getAttribute("loginResult") != null) {
			%>
			<p style="color: red">Updated Successfully....</p>
			<%
				session.removeAttribute("loginResult");
			}
			if (session.getAttribute("not") != null) {
			%>
			<p style="color: red">Email id is not velid.</p>
			<%
				session.removeAttribute("not");
			} else if (session.getAttribute("wrong") != null) {
			%>
			<p style="color: red">Email is invalid</p>
			<%
				session.removeAttribute("wrong");
			}
			%>
			<h3>Edit Profile</h3>
			<form action="<%=request.getContextPath()%>/Company" method="post">
				<c:forEach items="${sessionScope.data }" var="q">
			Company_name:-<input type="text" name="companyname"
						value="${q.companyname }"required>
					<br>
					<br>
			Address:-<input type="text" name="companyaddress"
						value="${q.address }"required>
					<br>
					<br>
			Email:-<input type="email" name="email" value="${q.email }"required>
					<br>
					<br>
			Password:-<input type="text" name="password" value="${q.password }"required>
					<br>
					<br>
					<input type="hidden" name="id" value="${q.id}"required>
					<input type="hidden" name="flag" value="Update">
				</c:forEach>
				<input type="submit">
			</form>
		</div>
	</div>
</body>
</html>