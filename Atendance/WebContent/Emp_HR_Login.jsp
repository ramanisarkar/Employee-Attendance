<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
	background-color: #4CAF50;
	color: white;
}

li a:hover:not(.active) {
	background-color: #555;
	color: white;
}
</style>
</head>
<body>
	<div>
		<ul>
			<c:forEach items="${sessionScope.data }" var="q">
				<li><a href="Emp_HR_Login.jsp">Home</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Employee?flag=insert&id=${q.compnayid.id }&flag2=hr">Add
						Employee</a></li>
				<%
					if (session.getAttribute("empmessage") != null) {
				%>
				<li><a
					href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=hr&flag3=hr&id=${q.compnayid.id }">View
						Notification <span style="color: red;"> &#9679 </span>
				</a></li>
				<%
					} else if (session.getAttribute("empmessage") == null) {
				%>
				<li><a
					href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=hr&flag3=hr&id=${q.compnayid.id }">View
						Notification</a></li>
				<%
					}
				%>
				<li><a
					href="<%=request.getContextPath()%>/Department?flag=hrdepartmentsearch&id=${q.compnayid.id }">View
						Department</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Employee?flag=companysearch&flag2=hr&id=${q.compnayid.id }">View
						Employee</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Attendance?flag=companyemployeepresent&flag2=hr&id=${q.compnayid.id }">Present
						Employee</a></li>
				<li><a href="Attendance?flag=empatt&flag2=hr&id=${q.id }">Attendance
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

					<a href="Employee?flag=Entery&flag2=hr&id=${q.id }"> In </a> <a
						href="Employee?flag=Exite&flag2=hr&id=${q.id }"> Out </a>
				</div>
			</c:forEach>
		</div>
		<div style="padding-top: 1%;">
			<table border="1">
				<tr>
					<td>First_name</td>
					<td>Last_name</td>
					<td>Contact_no</td>
					<td>Designation</td>
					<td>Address</td>
					<td>Email</td>
				</tr>
				<c:forEach items="${sessionScope.data }" var="q" end="0">
					<tr>
						<td>${q.firstName }</td>
						<td>${q.lastName }</td>
						<td>${q.con_no }</td>
						<td>${q.designation }</td>
						<td>${q.address }</td>
						<td>${q.email }</td>
					</tr>
				</c:forEach>
			</table>
			<c:forEach items="${sessionScope.data }" var="q" end="0">
				<div style="margin-top: 1%;">
					<a
						href="<%=request.getContextPath()%>/Employee?flag=EmployeeEdit&flag2=hr&id=${q.id }">Edit
						Profile</a>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>