<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">	
	<h1>Please change the password</h1>
	<form action="adminPasswordValidation" method="post">
	<table>		
		<tr>			
			<td>First Name: </td>
			<td><input type="text" name="userFirstName" placeholder="${userFirstName}" 
			value="${userFirstName}" disabled>
			<input type="hidden" name="userFirstName" placeholder="${userFirstName}" 
			value="${userFirstName}"></td>						
		</tr>
		<tr>
			<td>Last Name: </td>
			<td><input type="text" name="userLastName" placeholder="${userLastName}" 
			value="${userLastName}" disabled>
			<input type="hidden" name="userLastName" placeholder="${userLastName}" 
			value="${userLastName}"></td>			
		</tr>
		<tr>
			<td>Password: </td>
			<td><input type="text" name="userPassword" placeholder="${userPassword}"></td>			
		</tr>
		<tr>			
			<td>Contact No.: </td>
			<td><input type="number" name="userContactNo" placeholder="${userContactNo}" 
			value="${userContactNo}" disabled>
			<input type="hidden" name="userContactNo" placeholder="${userContactNo}" 
			value="${userContactNo}"></td>			
		</tr>
		<tr>			
			<td>Email ID: </td>
			<td><input type="text" name="userEmailID" placeholder="${userEmailID}" 
			value="${userEmailID}" disabled>
			<input type="hidden" name="userEmailID" placeholder="${userEmailID}" 
			value="${userEmailID}"></td>			
		</tr>
		<tr>			
			<td>Card No.: </td>
			<td><input type="number" name="userCardNo" placeholder="${userCardNo}" 
			value="${userCardNo}" disabled>
			<input type="hidden" name="userCardNo" placeholder="${userCardNo}" 
			value="${userCardNo}"></td>			
		</tr>
		<tr>			
			<td>Card Expiry: </td>
			<td><input type="text" size="30" name="userCardExpiry"
			placeholder="<fmt:formatDate value="${userCardExpiry}" pattern="MMMMM dd yyyy" />" 
			value="<fmt:formatDate value="${userCardExpiry}" pattern="MMMMM dd yyyy" />" 
			disabled>
			<input type="hidden" size="30" name="userCardExpiry"
			placeholder="<fmt:formatDate value="${userCardExpiry}" pattern="MMMMM dd yyyy" />" 
			value="<fmt:formatDate value="${userCardExpiry}" pattern="MMMMM dd yyyy" />">
			</td>			
		</tr>
		<tr><td></td><td><input type="hidden" value="${firstname}" name="firstname"></td></tr>
	</table><br>
	<span style="color:red">${errorMessage}</span><br/>	
	<table>							
		<tr>
			<input type="submit" value="Submit"/>			
		</tr>		
	</table>
	</form>
	</div>

</body>
</html>