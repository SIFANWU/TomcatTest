<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Homepage</title>
</head>
<body>
	<div class="w3-top">
  		<div class="w3-bar w3-white w3-wide w3-padding w3-card">
    		<a href="#home" class="w3-bar-item w3-button"><b>Cloud</b>Platform</a>
    			<div class="w3-right w3-hide-small">
    			<c:if test="${user==null}">
					<a href="${pageContext.request.contextPath}/Servlet/LoginUIServlet" class="w3-bar-item w3-button">Sign in</a>
					<a href="${pageContext.request.contextPath}/Servlet/RegisterUIServlet" class="w3-bar-item w3-button">Register</a>
				</c:if>
      			<c:if test="${user!=null}">
					<a class="w3-bar-item w3-button">Welcome: <b>${user.username}</b></a> 
				 	<a href="${pageContext.request.contextPath}/Servlet/LogoutServlet" class="w3-bar-item w3-button">Sign out</a>
				 	<a href="${pageContext.request.contextPath}/Servlet/AccountUIServlet" class="w3-bar-item w3-button">Bank</a>
				 	<a href="${pageContext.request.contextPath}/Servlet/UploadUIServlet" class="w3-bar-item w3-button">Upload</a>
				</c:if>	
				
   				</div>
  		</div>
	</div>
	
<!-- Header -->
<header class="w3-display-container w3-content w3-wide" style="max-width:1500px;">
  <img class="w3-image" src="images/sheffield.jpg" alt="University of Sheffield" width="1500" height="800">
  <div class="w3-display-middle w3-margin-top w3-center">
    <h1 class="w3-xxlarge w3-text-white"><span class="w3-padding w3-black w3-opacity-min"><b>Computer</b></span> <span class="w3-hide-small w3-text-light-grey">Science</span></h1>
  </div>
</header>	

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px">

  <div class="w3-container w3-padding-32" id="about">
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Applications</h3>
    <p> The user can load applications on this platform</p>
  </div>

  <div class="w3-row-padding w3-grayscale">
    <div class="w3-col l3 m6 w3-margin-bottom">
      <img src="images/uos.jpg"  alt="Application" style="width:100%; height: 200px">
      <h3>UoS</h3>
      <p class="w3-opacity">Cano</p>
      <p>The application for the University of Sheffield baseball club!</p>
      <p><c:if test="${user==null}"><a href="${pageContext.request.contextPath}/Servlet/LoginUIServlet" class="w3-button w3-light-grey w3-block">Click to login</a></c:if></p>
      <p><c:if test="${user!=null}"><a href='${pageContext.request.contextPath}/Servlet/AccountServlet' class="w3-button w3-light-grey w3-block">Use this application</a></c:if></p>
    </div>
  </div>
</div>
<!-- footer-->
<footer class="w3-center w3-black w3-padding-16">
  <p> Cloud Platform</p>
</footer>		
	
	
	</body>
</html>