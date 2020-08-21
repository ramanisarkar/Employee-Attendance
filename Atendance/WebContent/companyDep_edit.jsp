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

<title>Insert title here</title>
</head>
<body>
<div>
	<ul>
		<c:forEach items="${sessionScope.data }" var="q">
			 <li><a href="Company_Login.jsp">Home</a></li>
	 		 <li><a href="<%=request.getContextPath()%>/Department_controller?flag=insert&id=${q.id }">Add Department</a></li>
    		 <li><a href="Com_Login.jsp">Logout</a></li>
	  </c:forEach>
	</ul>
</div>
<div style="margin-left:25%;padding:1px 16px;height:1000px;">
	<div style="padding-top:2%;" >
		<%
	    if(session.getAttribute("loginResult") != null ){
		%>
		 <p style="color:red"> Updated Successfully.... </p>
		<%session.removeAttribute("loginResult");
		}
		%>
		<form action="<%=request.getContextPath() %>/Department_controller" method="post">
			<c:forEach items="${sessionScope.companydepartmentedit }" var="q" end="0">
			FirstName:-<input type="text" name="fn" value="${q.firstName }"><br><br>
			LastName:-<input type="text" name="ln" value="${q.lastName }"><br><br>
			Contact_NO:-<input type="text" name="cn" value="${q.con_no }"><br><br>
			Department:-<input type="text" name="department" value="${q.department }"><br><br>
			Email:-<input type="text" name="en" value="${q.email }"><br><br>
			Salary:-<input type="text" name="sn" value="${q.salary }"><br><br>
			<input type="hidden" name="status" value="${q.status }">
			<input type="hidden" name="com_id" value="${q.com_id.id }">
			<input type="hidden" name="id" value="${q.id }">
			<input type="hidden" name="gender" value="${q.gender }">
			<input type="hidden" name="pass" value="${q.password }">
			</c:forEach>
			<input type="hidden" name="flag" value="companyUpdate">
			<input type="submit" name="Submit">
		</form>
	</div>
</div>
</body>
</html>