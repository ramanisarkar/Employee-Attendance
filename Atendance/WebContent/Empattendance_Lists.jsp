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
				String s1 = (String) session.getAttribute("attendance");
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
				} else if (session.getAttribute("emp") == null) {
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
	<div style="margin-left: 25%; padding: 6px 16px; height: 1000px;">
		<%
			if (session.getAttribute("update") != null) {
		%>
		<p style="color: red">successfully Update......</p>
		<%
			session.removeAttribute("update");
		} else if (session.getAttribute("error") != null) {
		%>
		<p style="color: red">try Again.</p>
		<%
			session.removeAttribute("error");
		}
		%>
		<h3>Attendance Record</h3>
		<table border="1">
			<tr>
				<td>No</td>
				<td>Date</td>
				<td>IN_Time</td>
				<td>Out_time</td>
				<td>Status</td>
				<td>Edit</td>
			</tr>

			<%
				int i = 1;
			%>
			<c:forEach items="${sessionScope.empAttendanceout }" var="a">
				<tr>
					<td><%=i%></td>
					<td>${a.attout_id.date }</td>
					<td>${a.attout_id.start }</td>
					<td>${a.end }</td>
					<td>${a.status }</td>
					<%
						if (s1.equals("hr")) {
					%>
					<td><a
						href="<%=request.getContextPath()%>/Notification?flag=sendmesssage&flag2=hr&empid=${a.empattout_id.id }&attid=${a.id }">Message</a></td>
					<%
						} else if (s1.equals("head")) {
					%>
					<td><a
						href="<%=request.getContextPath()%>/Notification?flag=sendmesssage&flag2=head&empid=${a.empattout_id.id }&attid=${a.id }">Message</a></td>
					<%
						} else if (s1.equals("emp")) {
					%>
					<td><a
						href="<%=request.getContextPath()%>/Notification?flag=sendmesssage&flag2=emp&empid=${a.empattout_id.id }&attid=${a.id }">Message</a></td>
					<%
						}
					%>

					<%
						i++;
					%>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>