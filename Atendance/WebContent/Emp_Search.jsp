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
	background-color: #4CAF50;
	color: white;
}

li a:hover:not(.active) {
	background-color: #555;
	color: white;
}

.previous {
	background-color: #f1f1f1;
	color: black;
}

a {
	text-decoration: none;
	display: inline-block;
	padding: 8px 16px;
}

a:hover {
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
				<%
					}
				%>
				<li><a
					href="<%=request.getContextPath()%>/Attendance?flag=empatt&flag2=head&id=${q.id }">Attendance
						Record</a></li>
				<li><a
					href="<%=request.getContextPath()%>/Attendance?flag=companyemployeepresent&flag2=head&id=${q.dep_id.id }">Present
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
			<p style="color: red">Add Successfully....</p>
			<%
				session.removeAttribute("loginResult");
			}
			%>
			<%
				if (session.getAttribute("viewbutton") != null) {

				if (s1.equals("company")) {
			%>
			<c:forEach items="${sessionScope.data }" var="q">
				<a class="previous"
					href="<%=request.getContextPath()%>/Department?flag=departmentsearch&id=${q.id }">&laquo;
					Previous</a>
			</c:forEach>
			<%
				} else if (s1.equals("hr")) {
			%>
			<c:forEach items="${sessionScope.data }" var="q">
				<a class="previous"
					href="<%=request.getContextPath()%>/Department?flag=hrdepartmentsearch&id=${q.compnayid.id }">&laquo;
					Previous</a>
			</c:forEach>

			<%
				}session.removeAttribute("viewbutton");
			}
			%>
			<table border="1">
				<tr>
					<td>No</td>
					<td>FirstName</td>
					<td>LastName</td>
					<td>Contact_no</td>
					<td>Roll</td>
					<td>Designation</td>
					<td>Department</td>
					<td>Address</td>
					<td>Salary</td>
					<td>Email</td>
					<td>Edit</td>
					<td>delete</td>
					<td>Attendance Record</td>
				</tr>
				<%
					int i = 1;
				%>
				<c:forEach items="${sessionScope.emplist }" var="q">
					<tr>
						<td><%=i%></td>
						<td>${q.firstName }</td>
						<td>${q.lastName }</td>
						<td>${q.con_no }</td>
						<td>${q.roll }</td>
						<td>${q.designation }</td>
						<td>${q.dep_id.department }</td>
						<td>${q.address }</td>
						<td>${q.salary }</td>
						<td>${q.email }</td>
						<%
							if (s1.equals("company")) {
						%>
						<td><a
							href="Employee?flag=companyEdit&flag2=company&id=${q.id }&comid=${q.compnayid.id }">edit</a></td>
						<td><a href="Employee?flag=delete&flag2=company&id=${q.id }">delete</a></td>
						<td><a
							href="Attendance?flag=attendancerecord&flag2=company&id=${q.id }">Attendance
								Record</a></td>
						<%
							} else if (s1.equals("hr")) {
						%>
						<td><a
							href="Employee?flag=EmployeeEdit&flag2=hr&id=${q.id }&comid=${q.compnayid.id }">edit</a></td>
						<td><a href="Employee?flag=delete&flag2=hr&id=${q.id }">delete</a></td>
						<td><a
							href="Attendance?flag=attendancerecord&flag2=hr&id=${q.id }">Attendance
								Record</a></td>
						<%
							} else if (s1.equals("head")) {
						%>
						<td><a
							href="Employee?flag=EmployeeEdit&flag2=head&id=${q.id }&comid=${q.compnayid.id }">edit</a></td>
						<td><a href="Employee?flag=delete&flag2=head&id=${q.id }">delete</a></td>
						<td><a
							href="Attendance?flag=attendancerecord&flag2=head&id=${q.id }">Attendance
								Record</a></td>
						<%
							}
						i++;
						%>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>