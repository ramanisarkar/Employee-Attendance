<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
	  <c:forEach items="${sessionScope.data }" var="q">
		 <li><a href="Company_Login.jsp">Home</a></li>
 		 <li><a href="<%=request.getContextPath()%>/Department?flag=insert&id=${q.id }">Add Department</a></li>
 		 <li><a href="<%=request.getContextPath()%>/Employee?flag=insert&id=${q.id }">Add Employee</a></li>
 		 <li><a href="<%=request.getContextPath()%>/Department?flag=departmentsearch&id=${q.id }">All Department</a></li>
 		 <li><a href="<%=request.getContextPath()%>/Employee?flag=companysearch&id=${q.id }">All Employee</a></li>
   		 <li><a href="Com_Login.jsp">Logout</a></li>
	  </c:forEach>
	</ul>
</div>
<div style="margin-left:25%;padding:1px 16px;height:1000px;">
	<div style="padding-top:2%;" >
		<% 
	    if(session.getAttribute("loginResult") != null ){
		%>
		 <p style="color:red"> Add Successfully.... </p>
		<%session.removeAttribute("loginResult");
		}
		%>
		<table border="1">
		<tr>
		<td>Department</td>
		<td>View Employee</td>
		</tr >
			<c:forEach items="${sessionScope.listdepartment }" var="q">
				<tr>
				<td>${q.department }</td>
				<td><a href="<%=request.getContextPath()%>/Employee?flag=companydepartmentsearchemployee&flag2=department&id=${q.id }">All Employee</a></td>
				</tr>
			</c:forEach>	
		</table>
	</div>
</div>
</body>
</html>