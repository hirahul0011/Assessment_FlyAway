<%-- <%@page import="org.springframework.ui.ModelMap"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String firstname= (String)request.getAttribute("firstname");
request.setAttribute("firstname", firstname);
%>
	
	<div align="center">
	<h1>Welcome ${firstname}! Please Enter Your Travel Details</h1><!-- Could not work -->
	<!-- <h1>Welcome! Please Enter Your Travel Details</h1> -->
	<form action="searchFormValidation" method="post">
	<table>
		<tr><td>Enter Travel Date:&nbsp; </td><td><input type="date" name="travelDate"></td></tr>
		<tr><td>Enter Source:&nbsp;			
		
		</td><td><SELECT size=1 name='source' >
		<OPTION value=""></OPTION>  <!-- VVIMP How to give the empty string to Option -->
		<c:forEach items="${places}" var="place">
			<OPTION value="${place}">${place}</OPTION>
		</c:forEach>		
		</SELECT></td></tr>
		
		<tr><td>Enter Destination:&nbsp; 
		
		</td><td><SELECT size=1 name='destination' >
		<OPTION value=""></OPTION>  <!-- VVIMP How to give the empty string to Option -->
		<c:forEach items="${places}" var="place">
			<OPTION value="${place}">${place}</OPTION>
		</c:forEach>		
		</SELECT></td></tr>
		
		<tr><td>Enter Number of Persons:&nbsp; </td><td><input type="text" name="noOfPersons"></td></tr>
		<tr><td>Enter Preferred Airline:&nbsp; 
		
		</td><td><SELECT size=1 name='airline' >
		<OPTION value=""></OPTION>  <!-- VVIMP How to give the empty string to Option -->
		<c:forEach items="${airlines}" var="airline">
			<OPTION value="${airline}">${airline}</OPTION>
		</c:forEach>		
		</SELECT></td></tr>
		
		<tr><td></td><td><input type="hidden" value="${firstname}" name="firstname"></td></tr>
		
	</table><br><br>
		<span style="color:red">${errorMessage}</span><br/>
		&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<input type="submit" value="Search"/>
		
	</form>
	</div>
	

</body>
</html>