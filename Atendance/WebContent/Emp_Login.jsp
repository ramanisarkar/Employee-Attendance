<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	background-color: #555;
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
			<c:forEach items="${sessionScope.data }" var="q" end="0">
				<li><a class="active" href="Emp_Login.jsp">Home</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Employee?flag=EmployeeEdit&flag2=emp&id=${q.id }">Edit
						Profile</a></li>
				<li><a href="Attendance?flag=empatt&flag2=emp&id=${q.id }">Attendance
						Record</a></li>
				<li><a href="Com_Login.jsp">Logout</a></li>
			</c:forEach>
		</ul>
	</div>
	<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
		<div style="text-align: left;">
			<%
				out.print("Last Login Time :" + session.getAttribute("time"));
			%>
		</div>
		<div style="margin-top: 1%;">
			<c:forEach items="${sessionScope.data }" var="q" end="0">
				<div style="margin-top: 1%;">
					<%
						if (session.getAttribute("entry") != null) {
					%>
					<p style="color: red">IN Successfully</p>
					<%
						session.removeAttribute("entry");
					} else if (session.getAttribute("copydate") != null) {
					%>
					<p style="color: red">Attendance already exist</p>
					<%
						session.removeAttribute("copydate");
					} else if (session.getAttribute("checkattendance") != null) {
					%>
					<p style="color: red">IN time not entered</p>
					<%
						session.removeAttribute("checkattendance");
					} else if (session.getAttribute("out") != null) {
					%>
					<p style="color: red">OUT Successfully</p>
					<%
						session.removeAttribute("out");
					}
					%>

					<a href="Employee?flag=Entery&flag2=emp&id=${q.id }"> In </a> <a
						href="Employee?flag=Exite&flag2=emp&id=${q.id }"> Out </a>
				</div>
			</c:forEach>
		</div>
		<div style="padding-top: 1%;">
			<table border="1">
				<tr>
					<td>Id</td>
					<td>First_name</td>
					<td>Last_name</td>
					<td>Contact_no</td>
					<td>Designation</td>
					<td>Address</td>
					<td>Email</td>
				</tr>
				<c:forEach items="${sessionScope.data }" var="q" end="0">
					<tr>
						<td>${q.id }</td>
						<td>${q.firstName }</td>
						<td>${q.lastName }</td>
						<td>${q.con_no }</td>
						<td>${q.designation }</td>
						<td>${q.address }</td>
						<td>${q.email }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>