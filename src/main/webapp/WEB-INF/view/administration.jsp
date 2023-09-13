<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	<h1>${firstname}! Below are the details for all the Users</h1>
	<!-- <h1>As per your submission below are the available flights</h1>	 -->
	<table>
		<tr>
			<td><b>FirstName</b></td>
			<td><b>LastName</b></td>
			<td><b>Password</b></td>
			<td><b>ContactNo</b></td>
			<td><b>EmailID</b></td>
			<td><b>CardNo</b></td>
			<td><b>CardExpiry</b></td>
			<td></td>			
		</tr>
		<c:forEach items="${users}" var="user">			
			<tr>
			<td> ${user.firstName}</td>
			<td> ${user.lastName}</td>
			<td> ${user.password}</td>
			<td> ${user.contactNo}</td>
			<td> ${user.emailID}</td>
			<td> ${user.cardNo}</td>
			<td> <fmt:formatDate value="${user.cardExpiry}" pattern="MMMMM dd yyyy" /></td>			
			<td><a href="adminChangePassword?
			firstname=${firstname}
			&userFirstName=${user.firstName}
			&userLastName=${user.lastName}
			&userPassword=${user.password}
			&userContactNo=${user.contactNo}
			&userEmailID=${user.emailID}
			&userCardNo=${user.cardNo}
			&userCardExpiry=<fmt:formatDate value="${user.cardExpiry}" pattern="MMMMM dd yyyy" />
			">Change Password</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>

</body>
</html>