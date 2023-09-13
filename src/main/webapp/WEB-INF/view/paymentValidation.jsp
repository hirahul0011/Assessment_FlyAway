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
	<h1>Please enter the OTP sent to your mobile number</h1>
	<form action="otpValidation" method="post">
	<table>
		<tr>
			<td>Card: </td>
			<td>${userCardNo}</td>			
		</tr>
		<tr>
			<td>Amount to be debited: </td>
			<td>Rs ${billingAmount}</td>			
		</tr>
		<tr>
			<td>OTP: </td>
			<td><input type="number" name="otp"></td>			
		</tr>
	</table><br>
		<c:set var="i" value="1" scope="page" />	
		<c:set var="passengerDetails" value="" scope="page" />		
		<c:forEach items="${passengerList}" var="passenger">
		
			<input type="hidden" value="${passenger.name}" name="p${i}">
			<input type="hidden" value="${passenger.sex}" name="sex${i}">
			<input type="hidden" value="${passenger.age}" name="age${i}">		
			
		<c:set var="passengerDetails" value="${passengerDetails}&p${i}=${passenger.name}
		&sex${i}=${passenger.sex}
		&age${i}=${passenger.age}" scope="page"/>
		<c:set var="i" value="${i + 1}" scope="page"/>		
		</c:forEach>
		<span style="color:red">${errorMessage}</span><br/>
	<table>	
			<input type="hidden" value="${firstname}" name="firstname">
			<input type="hidden" value="${userContactNo}" name="userContactNo">
			<input type="hidden" value="${userEmailID}" name="userEmailID">
			<input type="hidden" value="${userCardNo}" name="userCardNo">
			<input type="hidden" value="${userCardExpiry}" name="userCardExpiry">
			<input type="hidden" value="${ID}" name="ID">
			<input type="hidden" value="${source}" name="source">
			<input type="hidden" value="${destination}" name="destination">
			<input type="hidden" value="${airline}" name="airline">
			<input type="hidden" value="${price}" name="price">
			<input type="hidden" value="${noOfPersons}" name="noOfPersons">
			<input type="hidden" value="<fmt:formatDate value="${travelDate}" pattern="EEEEE MMMMM dd yyyy" />" name="travelDate">
			<input type="hidden" value="${billingAmount}" name="billingAmount">						
		<tr>						
			<td><input type="submit" value="Submit"></td>
			<!-- &passengerList=${passengerList} -->
		</tr>		
	</table>
	</form>	
	</div>

</body>
</html>