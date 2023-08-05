<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link href="css/loginStyle.css" rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/allBooksStyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<body>
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->

			<!-- Icon -->
			<div class="fadeIn first mt-3">
				<h2><b>Sign In</b></h2>
			</div>

			<!-- Login Form -->
			<form:form method="post" modelAttribute="user">
				<form:input type="text" id="username" class="fadeIn second" name="username"
					placeholder="username" path="username" /> 
				<form:errors path="username" cssClass="text-danger"></form:errors>
				<form:input type="password" id="password"
					class="fadeIn third" name="password" placeholder="password" path="password" /> 
				<form:errors path="password" cssClass="text-danger"></form:errors>
				<br/>
				<input
					type="submit" class="fadeIn fourth" value="Sign In" />
			</form:form>
		</div>
	</div>

</body>
<!-- Custom error alert -->
<c:if test="${not empty customError}">
    <script>
        alert("${customError}");
    </script>
</c:if>
</html>