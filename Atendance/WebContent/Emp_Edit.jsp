<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
			<%
				String s1 = (String) session.getAttribute("edit");
			System.out.print(s1);
			if (s1.equals("hr")) {
			%>
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
			<%
				} else if (s1.equals("head")) {
			%>
			<c:forEach items="${sessionScope.data }" var="q">
				<li><a href="Emp_Admin_Login.jsp">Home</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Employee?flag=departmentsearchemployee&flag2=head&id=${q.dep_id.id }">View
						Employee</a></li>
				<%
					if (session.getAttribute("empmessage") != null) {
				%>
				<li><a
					href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=head&flag3=head&id=${q.dep_id.id }">View
						Notification <span style="color: red;"> &#9679 </span>
				</a></li>
				<%
					} else if (session.getAttribute("empmessage") == null) {
				%>
				<li><a
					href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=head&flag3=head&id=${q.dep_id.id }">View
						Notification</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Attendance?flag=empatt&flag2=head&id=${q.id }">Attendance
						Record</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Attendance?flag=companyemployeepresent&flag2=head&id=${q.dep_id.id }">Present
						Employee</a></li>
				<%
					}
				%>
				<li><a href="Com_Login.jsp">Logout</a></li>
			</c:forEach>
			<%
				} else if (s1.equals("emp")) {
			%>
			<c:forEach items="${sessionScope.data }" var="q" end="0">
				<li><a class="active" href="Emp_Login.jsp">Home</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Employee?flag=EmployeeEdit&flag2=emp&id=${q.id }">Edit
						Profile</a></li>
				<li><a href="Attendance?flag=empatt&flag2=emp&id=${q.id }">Attendance
						Record</a></li>
				<li><a href="Com_Login.jsp">Logout</a></li>
			</c:forEach>
			<%
				}
			%>
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
			} else if (session.getAttribute("emailrecord") != null) {
			%>
			<p style="color: red">Email id is already exist</p>
			<%
				session.removeAttribute("email");
			} else if (session.getAttribute("wrong") != null) {
			%>
			<p style="color: red">Email is invalid</p>
			<%
				session.removeAttribute("wrong");
			}
			%>
			<form action="<%=request.getContextPath()%>/Employee" method="post">
				<c:forEach items="${sessionScope.emplist }" var="q" end="0">
			FirstName:-<input type="text" name="firstName"
						value="${q.firstName }" required>
					<br>
					<br>
			LastName:-<input type="text" name="lastName" value="${q.lastName }"required>
					<br>
					<br>
			Contact_NO:-<input type="text" name="Con_no" value="${q.con_no }"required>
					<br>
					<br>
			Address:-<input type="text" name="address" value="${q.address }"required>
					<br>
					<br>
			Email:-<input type="text" name="email" value="${q.email }"required>
					<br>
					<br>
			Password:-<input type="text" name="password" value="${q.password }"required>
					<input type="hidden" name="dep_id" value="${q.dep_id.id }">
					<input type="hidden" name="roll" value="${q.roll }">
					<input type="hidden" name="designation" value="${q.designation }">
					<br>
					<br>
					<%
						if (s1.equals("hr") || (s1.equals("head"))) {
					%>
			Salary:-<input type="text" name="salary" value="${q.salary }"required>
					<br>
					<br>
					<%
						} else if (s1.equals("emp")) {
					%>
					<input type="hidden" name="salary" value="${q.salary }">
					<%
						}
					%>
					<input type="hidden" name="id" value="${q.id}">
					<input type="hidden" name="gender" value="${q.gender }">
					<input type="hidden" name="comid" value="${q.compnayid.id }">
					<input type="hidden" name="flag" value="employeeUpdate">
				</c:forEach>
				<input type="submit">
			</form>
		</div>
	</div>
</body>
</html>