<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.sql.Time"%>
<%@page import="VO.EmpAttendanceVo"%>
<%@page import="VO.EmpAttendance_outVo"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
			<h3>Present Employee</h3>
			<form action="Attendance" method="post">
				<input type="date" name="search" required="required"> <input
					type="hidden" name="flag2" value="<%=s1%>">
				<c:forEach items="${sessionScope.data }" var="q" end="0">
					<%
						if (s1.equals("company")) {
					%>
					<input type="hidden" name="id" value="${q.id }">
					<%
						} else if (s1.equals("hr")) {
					%>
					<input type="hidden" name="id" value="${q.compnayid.id }">
					<%
						} else if (s1.equals("head")) {
					%>
					<input type="hidden" name="id" value="${q.dep_id.id }">
					<%
						}
					%>
				</c:forEach>
				<input type="hidden" name="flag3" value="search"> 
				<input type="hidden" name ="flag" value="searchempaloyee"> 
				<input type="submit" value="Search">
			</form>
			<br>
			<%out.println(session.getAttribute("dateprint")); %>
			<br>
			<table border="1">
				<tr>
					<td>No</td>
					<td>Employee Name</td>
					<td>IN_Time</td>
					<td>Department</td>
					<td>Designation</td>
				</tr>

				<%
					int i = 1;
				List<EmpAttendanceVo> list = (List) session.getAttribute("presentattendance");
				for (EmpAttendanceVo employeeVo : list) {
				%>
				<tr>
					<td><%=i%></td>
					<td>
						<%
							out.println(employeeVo.getEmpattendance().getFirstName());
						%>
					</td>
					<td>
						<%
							java.sql.Time time = employeeVo.getStart();
							LocalTime localtime = time.toLocalTime();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
							out.println(localtime.format(formatter));
						%>
					</td>
					<td>
						<%
							out.println(employeeVo.getEmpattendance().getDep_id().getDepartment());
						%>
					</td>
					<td>
						<%
							out.println(employeeVo.getEmpattendance().getRoll());
						%>
					</td>
					<%
						i++;
					%>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

</body>
</html>