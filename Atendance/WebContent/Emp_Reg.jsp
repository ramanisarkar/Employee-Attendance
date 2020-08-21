
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	background-color: #4CAF50;
	color: white;
}

li a:hover:not (.active ) {
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
				String s1 = (String) session.getAttribute("flag");
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
			} else if (session.getAttribute("erorr") != null) {
			%>
			<p style="color: red">Email id already exists</p>
			<%
				session.removeAttribute("erorr");
			}
			if (session.getAttribute("select") != null) {
			%>
			<p style="color: red">Please select your roll</p>
			<%
				session.removeAttribute("select");
			}
			if (session.getAttribute("department") != null) {
			%>
			<p style="color: red">Please select your department</p>
			<%
				session.removeAttribute("department");
			}
			if (session.getAttribute("existdepartment") != null) {
			%>
			<p style="color: red">Department Head already exist</p>
			<%
				session.removeAttribute("existdepartment");
			}
			if (session.getAttribute("wrong") != null) {
			%>
			<p style="color: red">Email is invalid</p>
			<%
				session.removeAttribute("wrong");
			}
			%>

			<h3>Add Employee</h3>
			<form action="<%=request.getContextPath()%>/Employee" method="post">
				<span>*</span> First_Name: <input type="text" name="firstName"
					required><br> <br> <span>*</span>Last_Name: <input
					type="text" name="lastName" required><br> <br> <span>*</span>Contact_No:
				<input type="text" name="Con_no" required><br> <br>
				<span>*</span>Designation:<input type="text" name="designation"
					required><br> <br> <span>*</span>Department:<br>
				<select name="department" required>
					<option>Select</option>
					<c:forEach items="${sessionScope.listdepartment }" var="q">
						<option value="${q.id }">${q.department }</option>
					</c:forEach>
				</select><br> <br> <span>*</span>Roll:-<select name="roll" required>
					<option>Select</option>
					<option>Emp</option>
					<option>Head</option>
				</select><br> <br> <span>*</span>Address:
				<textarea rows="5" cols="10" name="address" required></textarea>
				<br> <br> <span>*</span>Gender:<br> Male: <input
					type="radio" name="gender" value="male" required><br>
				Female: <input type="radio" name="gender" value="female" required><br>
				<br> <span>*</span>Salary: <input type="text" name="salary"
					required><br> <br> <span>*</span>Email: <input
					type="email" name="email" required><br> <br> <span>*</span>Password:
				<input type="password" name="pass" required><br /> <br />
				<input type="hidden" name="flag" value="insert" /> <input
					type="submit" value="SUBMIT" />
			</form>
		</div>
	</div>
</body>
</html>