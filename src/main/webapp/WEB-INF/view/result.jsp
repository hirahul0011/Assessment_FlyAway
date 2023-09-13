<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!-- VVIMP I did not included this taglib initially -->  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	<h1>${firstname}! As per your submission below are the available flights</h1>
	<!-- <h1>As per your submission below are the available flights</h1>	 -->
	<table>
		<tr>
			<td>ID</td>
			<td>source</td>
			<td>destination</td>
			<td>airline</td>
			<td>price</td>
			<td></td>
		</tr>
		<c:forEach items="${flights}" var="flight">			
			<tr>
			<td> ${flight.ID}</td>
			<td> ${flight.source} </td>
			<td> ${flight.destination}</td>
			<td> ${flight.airline}</td>
			<td> ${flight.price}</td>
			<td><a href="registerPassengerDetails?
			firstname=${firstname}
			&ID=${flight.ID}
			&source=${flight.source}
			&destination=${flight.destination}
			&airline=${flight.airline}
			&price=${flight.price}
			&noOfPersons=${noOfPersons}
			&travelDate=${travelDate}">Select</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>		

</body>
</html>