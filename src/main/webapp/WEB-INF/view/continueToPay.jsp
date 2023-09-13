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
	<h1>Please enter the CVV code for the Card and Pay</h1>
	<form action="cvvCodeValidation" method="post">
	<table>
		<tr>
			<td>Card: </td>
			<td>${userCardNo}</td>			
		</tr>
		<tr>
			<td>Expiry Date: </td>
			<td><fmt:formatDate value="${userCardExpiry}" pattern="MMMMM dd yyyy" /></td>			
		</tr>
		<tr>
			<td>CVV Code: </td>
			<td><input type="number" name="cvvCode"></td>			
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
			<td><input type="submit" value="Pay"></td>
			<!-- &passengerList=${passengerList} -->
		</tr>		
	</table>
	</form><br>
	<table>
		<%-- <% int i=1; %> --%>	
		<c:set var="i" value="1" scope="page" />	
		<c:set var="passengerDetails" value="" scope="page" />
		<%-- <c:forEach var="i" begin="1" end="${noOfPersons}"> --%>
		<c:forEach items="${passengerList}" var="passenger">		
			<tr>			
				<td>Name of Passenger ${noOfPersons>1?i:""} : </td>
				<!-- Link that helped  -->
				<!-- https://stackoverflow.com/questions/14482451/ternary-operator-in-jstl-el -->
				<td>${passenger.name}</td>						
			</tr>
			<tr>			
				<td>Sex of Passenger ${noOfPersons>1?i:""} : </td>
				<td>${passenger.sex}</td>						
			</tr>
			<tr>			
				<td>Age of Passenger ${noOfPersons>1?i:""} : </td>
				<td>${passenger.age}</td>						
			</tr>
			<tr><td>--------------------</td><td>--------------------</td></tr>	
			<%-- <% i++; %> --%>
		<c:set var="passengerDetails" value="${passengerDetails}&p${i}=${passenger.name}
		&sex${i}=${passenger.sex}
		&age${i}=${passenger.age}" scope="page"/>
		<c:set var="i" value="${i + 1}" scope="page"/>		
		</c:forEach>
		<tr>			
			<td>Date of Travel: </td>
			<td><fmt:formatDate value="${travelDate}" pattern="EEEEE MMMMM dd yyyy" /></td>						
		</tr>
		<tr>
			<td>Flight ID: </td>
			<td>${ID}</td>			
		</tr>
		<tr>
			<td>Boarding Airport: </td>
			<td>${source}</td>			
		</tr>
		<tr>			
			<td>Destination Airport: </td>
			<td>${destination}</td>			
		</tr>
		<tr>			
			<td>Airline: </td>
			<td>${airline}</td>			
		</tr>
		<tr>			
			<td>Seats Booked: </td>
			<td>${noOfPersons}</td>			
		</tr>
		<tr>			
			<td>Total Billing Amount: </td>
			<td>Rs ${billingAmount}</td>			
		</tr>
	</table>
	</div>

</body>
</html>