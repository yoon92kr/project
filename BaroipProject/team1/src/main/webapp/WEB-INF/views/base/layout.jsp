<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

	<!-- favicon -->
	<link rel="shortcut icon" type="image/png" href="../resource/image/common/favicon.png">

		<!-- google font -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
	<!-- fontawesome -->
	<link rel="stylesheet" href="../resource/css/all.min.css">
    
	<!-- bootstrap -->
	<link rel="stylesheet" href="../resource/bootstrap/css/bootstrap.min.css">
	<!-- main style -->
	<link rel="stylesheet" href="../resource/css/main.css">
	

	<!-- Title 배치 -->
	<title> <tiles:insertAttribute name="title" /> </title>
	
</head>

<body>



<tiles:insertAttribute name="header" />


<nav>
<tiles:insertAttribute name="side" />
</nav>


<tiles:insertAttribute name="body" />


<tiles:insertAttribute name="footer" />


<tiles:insertAttribute name="chat" />

	

</body>
</html>