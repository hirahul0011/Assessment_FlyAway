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
	<h1>Please complete your travel Details</h1>
	<form action="bookingDetailsCapture" method="post">
	<table>
		<c:forEach var="i" begin="1" end="${noOfPersons}">
			<tr>			
				<td>Name of Passenger ${noOfPersons>1?i:""}: </td>
				<td><input type="text" name="p${i}"></td>						
			</tr>
			<tr>			
				<td>Sex of Passenger ${noOfPersons>1?i:""}: </td>
				<td><SELECT size=1 name="sex${i}" } >
						<OPTION value="M">Male</OPTION>
						<OPTION value="F">Female</OPTION>
					</SELECT>
				</td>						
			</tr>
			<tr>			
				<td>Age of Passenger ${noOfPersons>1?i:""}: </td>
				<td><input type="number" name="age${i}"></td>						
			</tr>
			<tr><td>--------------------</td><td>--------------------</td></tr>		
		</c:forEach>
		<tr>			
			<td>Date of Travel: </td>
			<td><input type="text" size="30" name="travelDate"
			placeholder="<fmt:formatDate value="${travelDate}" pattern="EEEEE MMMMM dd yyyy" />" 
			value="<fmt:formatDate value="${travelDate}" pattern="EEEEE MMMMM dd yyyy" />" 
			disabled>
			<input type="hidden" size="30" name="travelDate"
			placeholder="<fmt:formatDate value="${travelDate}" pattern="EEEEE MMMMM dd yyyy" />" 
			value="<fmt:formatDate value="${travelDate}" pattern="EEEEE MMMMM dd yyyy" />">
			</td>						
		</tr>
		<tr>
			<td>Flight ID: </td>
			<td><input type="number" name="ID" placeholder="${ID}" 
			value="${ID}" disabled>
			<input type="hidden" name="ID" placeholder="${ID}" 
			value="${ID}"></td>			
		</tr>
		<tr>
			<td>Boarding Airport: </td>
			<td><input type="text" name="source" placeholder="${source}" 
			value="${source}" disabled>
			<input type="hidden" name="source" placeholder="${source}" 
			value="${source}"></td>			
		</tr>
		<tr>			
			<td>Destination Airport: </td>
			<td><input type="text" name="destination" placeholder="${destination}" 
			value="${destination}" disabled>
			<input type="hidden" name="destination" placeholder="${destination}" 
			value="${destination}"></td>			
		</tr>
		<tr>			
			<td>Airline: </td>
			<td><input type="text" name="airline" placeholder="${airline}" 
			value="${airline}" disabled>
			<input type="hidden" name="airline" placeholder="${airline}" 
			value="${airline}"></td>			
		</tr>
		<tr>			
			<td>Seats Booked: </td>
			<td><input type="number" name="noOfPersons" placeholder="${noOfPersons}" 
			value="${noOfPersons}" disabled>
			<input type="hidden" name="noOfPersons" placeholder="${noOfPersons}" 
			value="${noOfPersons}"></td>			
		</tr>
		<tr>			
			<td>Total Billing Amount: </td>
			<td><input type="number" name="billingAmount" placeholder="${billingAmount}" 
			value="${billingAmount}" disabled>
			<input type="hidden" name="billingAmount" placeholder="${billingAmount}" 
			value="${billingAmount}"></td>			
		</tr>
		<tr><td></td><td><input type="hidden" value="${firstname}" name="firstname"></td></tr>
	</table><br>
	<span style="color:red">${errorMessages}</span><br/>
	<table>							
		<tr>
			<input type="submit" value="Submit"/>			
		</tr>		
	</table>
	</form>
	</div>

</body>
</html>