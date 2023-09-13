<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	<h2>Please login to search for the Flights</h2>	
	<form action="validationCheck" method="post">
		Enter UserName: <input type="text" name="username"/><br/><br>		
		Enter Password: <input type="password" name="password"><br/>
		<span style="color:red">${errorMessage}</span><br/>
		<h6>Please use Admin UserName to login as Administrator</h6>
		<input type="submit" value="login">
	</form>
	</div>

</body>
</html>