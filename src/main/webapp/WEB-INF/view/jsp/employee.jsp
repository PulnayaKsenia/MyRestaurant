<%@ page import="java.io.File" %>
<%@ page import="java.net.URL" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<header>
    <div class="logo">
        <a href="/">Java-Pizza</a>
    </div>
    <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/employees">Employees</a></li>
            <li><a href="/dishes">Menu</a></li>
            <li><a href="/contacts">Contacts</a></li>
            <li><a href="/restaurantScheme">Restaurant Scheme</a></li>
        </ul>
    </nav>
</header>

<div class="employeeCentralPanel">

    <button class="selectedPositionButton" onclick="location.href='/employees';">< Back to list</button>;

    <div class="employeePhoto">
        <img width="250" height="300" src="${employee.photoURL}"/>
    </div>

    <table class="table">

        <tr>
            <th>First Name</th>
            <td>${employee.firstName}</td>
        </tr>
        <tr>
            <th>Position</th>
            <td>${employee.position}</td>
        </tr>
    </table>
</div>

<footer>
    <ul>
        <li>
            <img height="20" width="20" src="<c:url value="/images/location-icon.png"/>"/>
            Kharkov, Spring str. 13
        </li>
        <li>
            <img height="20" width="20" src="<c:url value="/images/phone-icon.png"/>"/>
            +38066-1234567
            +38096-1234567
        </li>
        <li>
            <img height="20" width="20" src="<c:url value="/images/email-icon.png"/>"/>
            javapizza@gmail.com
        </li>
    </ul>
</footer>

</body>
</html>
