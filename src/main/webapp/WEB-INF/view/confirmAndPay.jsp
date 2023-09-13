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
	<h1>Please confirm your personal details as well before moving ahead for payment</h1>
	<table>
		<tr>			
			<td>Contact No: </td>
			<td>${userContactNo}</td>						
		</tr>
		<tr>
			<td>Email ID: </td>
			<td>${userEmailID}</td>			
		</tr>
		<tr>
			<td>Card used for Payment: </td>
			<td>${userCardNo}</td>			
		</tr>
		<tr>
			<td>Card Expiry Date: </td>
			<td><fmt:formatDate value="${userCardExpiry}" pattern="MMMMM dd yyyy" /></td>			
		</tr>
	</table><br>
		<c:set var="i" value="1" scope="page" />	
		<c:set var="passengerDetails" value="" scope="page" />		
		<c:forEach items="${passengerList}" var="passenger">		
			
		<c:set var="passengerDetails" value="${passengerDetails}&p${i}=${passenger.name}
		&sex${i}=${passenger.sex}
		&age${i}=${passenger.age}" scope="page"/>
		<c:set var="i" value="${i + 1}" scope="page"/>		
		</c:forEach>
	<table>							
		<tr>			
			<td><a href="continueWithPayment?
			firstname=${firstname}
			${passengerDetails}
			&userContactNo=${userContactNo}
			&userEmailID=${userEmailID}
			&userCardNo=${userCardNo}
			&userCardExpiry=${userCardExpiry}
			&ID=${ID}
			&source=${source}
			&destination=${destination}
			&airline=${airline}
			&price=${price}
			&noOfPersons=${noOfPersons}
			&travelDate=<fmt:formatDate value="${travelDate}" pattern="EEEEE MMMMM dd yyyy" />
			&billingAmount=${billingAmount}">Continue To Pay</a></td>
			<!-- &passengerList=${passengerList} -->
		</tr>		
	</table><br>
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