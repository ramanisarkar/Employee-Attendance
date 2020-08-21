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
				String s1 = (String) session.getAttribute("attendancesearch");
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
				}
			%>
		</ul>
	</div>
	<div style="margin-left: 25%; padding: 6px 16px; height: 1000px;">

		<form action="Attendance" method="post">
			<c:forEach items="${sessionScope.empAttendanceout }" var="a" end="0">
				From:-<input type="date" name="from">
				To:- <input type="date" name="to">
				<input type="hidden" name="flag" value="searchattendance">
				<input type="hidden" name="empid" value="${a.empattout_id.id }">
				<input type="submit">
			</c:forEach>
		</form>
		<br>
		<%
			int present = 0;
		int leave = 0;
		int half = 0;
		%>

		<c:forEach items="${sessionScope.empAttendanceout }" var="q">

			<c:choose>
				<c:when test="${q.status eq 'Present'}">
					<%
						present = present + 1;
					%>
				</c:when>
				<c:when test="${q.status eq 'Leave'}">
					<%
						leave = leave + 1;
					%>
				</c:when>
				<c:otherwise>
					<%
						half = half + 1;
					%>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<%
			out.println(" Present = " + present + " Leave = " + leave + " Half Leave = " + half);
		%>
		<br> <br>
		<table border="1">
			<tr>
				<td>No</td>
				<td>Date</td>
				<td>IN_Time</td>
				<td>Out_time</td>
				<td>Status</td>
			</tr>

			<%
				int i = 1;
			%>
			<c:forEach items="${sessionScope.empAttendanceout }" var="a">
				<tr>
					<c:choose>
						<c:when test="${a.status eq 'Present'}">
							<td style="background-color: green;color: black;"><%=i%></td>
							<td style="background-color: green;color: black;">${a.attout_id.date }</td>
							<td style="background-color: green;color: black;">${a.attout_id.start }</td>
							<td style="background-color: green;color: black;">${a.end }</td>
							<td style="background-color: green;color: black;">${a.status }</td>
						</c:when>
						<c:when test="${a.status eq 'Leave'}">
							<td style="background-color: red;color: black;"><%=i%></td>
							<td style="background-color: red;color: black;">${a.attout_id.date }</td>
							<td style="background-color: red;color: black;">${a.attout_id.start }</td>
							<td style="background-color: red;color: black;">${a.end }</td>
							<td style="background-color: red;color: black;">${a.status }</td>
						</c:when>
						<c:when test="${a.status eq 'Half Leave'}">
							<td style="background-color:yellow ;color: black;"><%=i%></td>
							<td style="background-color:yellow ;color: black;">${a.attout_id.date }</td>
							<td style="background-color:yellow ;color: black;">${a.attout_id.start }</td>
							<td style="background-color:yellow ;color: black;">${a.end }</td>
							<td style="background-color:yellow ;color: black;">${a.status }</td>
						</c:when>
					</c:choose>
					<%
						i++;
					%>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>