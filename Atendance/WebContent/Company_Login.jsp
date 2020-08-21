<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>    
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
		<li><a href="Company_Login.jsp">Home</a></li>
 		 <li><a href="<%=request.getContextPath()%>/Department?flag=insert&id=${q.id }">Add Department</a></li>
 		 <li><a href="<%=request.getContextPath()%>/Employee?flag=insert&id=${q.id }&flag2=company">Add Employee</a></li>
		<%
		if(session.getAttribute("empmessage") != null ){
		%>
			<li>
			<a href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=hr&flag3=company&id=${q.id }">View Notification <span style="color:red;"> &#9679 </span></a>
		</li>
		<%}else if(session.getAttribute("empmessage") == null ){%>
		<li>
		<a href="<%=request.getContextPath()%>/Notification?flag=messsage&flag2=hr&flag3=company&id=${q.id }">View Notification</a>
		</li>
		<% }
		%>
 		 <li><a href="<%=request.getContextPath()%>/Department?flag=departmentsearch&id=${q.id }">View Department</a></li>
 		 <li><a href="<%=request.getContextPath()%>/Employee?flag=companysearch&flag2=company&id=${q.id }">View Employee</a></li>
 		 <li><a href="<%=request.getContextPath()%>/Attendance?flag=companyemployeepresent&flag2=company&id=${q.id }">Present Employee</a></li>
   		 <li><a href="Com_Login.jsp">Logout</a></li>
	  </c:forEach>
	</ul>
</div>
<div style="margin-left:25%;padding:1px 16px;height:1000px;">
	<div style="text-align: left;">
		<%out.print( "Last Login Time :"+session.getAttribute("time"));%>
	</div>
	<table border="1"style="margin-top: 2%;">
	<tr>
	<td>CompanyName</td>
	<td>Address</td>
	<td>Email</td>
	</tr>
		<c:forEach items="${sessionScope.data }" var="q" end="0">
			<tr>
			<td>${q.companyname }</td>
			<td>${q.address }</td>
			<td>${q.email }</td>
			</tr>
		</c:forEach>
	</table>
	<c:forEach items="${sessionScope.data }" var="q" end="0">
			<div style="margin-top: 1%;">		
			<a href="<%=request.getContextPath()%>/Company?flag=edit&id=${q.id }">Edit Profile</a>
			</div>
		</c:forEach>
</div>
</body>
</html>