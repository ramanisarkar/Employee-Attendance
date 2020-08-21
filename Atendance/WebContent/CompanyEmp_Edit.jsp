<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
			<%
				String s1 = (String) session.getAttribute("search");
			System.out.print(s1);
			if (s1.equals("company")) {
			%>
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
			<%
				} else if (s1.equals("hr")) {
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
					href="<%=request.getContextPath()%>/Attendance?flag=companyemployeepresent&flag2=hr&id=${q.id }">Present
						Employee</a></li>
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
			} else if (session.getAttribute("department") != null) {
			%>
			<p style="color: red">Department head already exist</p>
			<%
				session.removeAttribute("department");
			} else if (session.getAttribute("emailrecord") != null) {
			%>
			<p style="color: red">Email id is already exist</p>
			<%
				session.removeAttribute("emailrecord");
			}
			%>
			<form action="<%=request.getContextPath()%>/Employee" method="post">
				<c:forEach items="${sessionScope.emplist }" var="q">
			FirstName:-<input type="text" name="firstName"
						value="${q.firstName }"required>
					<br>
					<br>
			LastName:-<input type="text" name="lastName" value="${q.lastName }"required>
					<br>
					<br>
			Email:-<input type="text" name="email" value="${q.email }"required>
					<br>
					<br>
			Contact_No:-<input type="text" name="Con_no" value="${q.con_no }"required>
					<br>
					<br>
			Address:-<input type="text" name="address" value="${q.address }"required>
					<br>
					<br>
			Department:-
				<select name="dep_id"required>
						<option value="${q.dep_id.id }">${q.dep_id.department }</option>
						<c:forEach items="${sessionScope.listdepartment }" var="d">
							<option value="${d.id }">${d.department }</option>
						</c:forEach>
					</select>
					<br>
					<br>
			Roll:-
				<select name="roll"required>
						<option value="${q.roll }">${q.roll }</option>
						<option>Emp</option>
						<option>Head</option>
					</select>
					<br>
					<br>
			Designation:- <input type="text" name="designation"
						value="${q.designation }"required>
					<br>
					<br>
					<%
						if (s1.equals("company")) {
					%>
			Salary:-<input type="text" name="salary" value="${q.salary }"required>
					<br>
					<br>
					<%
						} else if (s1.equals("hr")) {
					%>
			Salary:-<input type="hidden" name="salary" value="${q.salary }"required>
					<br>
					<br>
					<%
						}
					%>
					<input type="hidden" name="password" value="${q.password }">
					<input type="hidden" name="gender" value="${q.gender }">
					<input type="hidden" name="comid" value="${q.compnayid.id }">
					<input type="hidden" name="id" value="${q.id}">
					<input type="hidden" name="flag" value="Update">
				</c:forEach>
				<input type="submit">
			</form>
		</div>
	</div>
</body>
</html>