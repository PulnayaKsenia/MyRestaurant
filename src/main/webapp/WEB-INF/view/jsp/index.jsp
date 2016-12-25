<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <title>Title</title>
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

<div class="indexCentralPanel">
    <h1>   Hello in our restaurant "Java-Pizza"</h1>
    <h1>       Eat pizza and make code ;) </h1>

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
