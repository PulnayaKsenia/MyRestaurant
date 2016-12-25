<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <c:if test="${correctPhoneNumber == false}">
        <script>
            alert("Incorrect phone number");
        </script>
    </c:if>
</head>
<body>

<header>
    <nav>
        <ul>
            <li><a href="/admin/employees">Employees</a></li>
            <li><a href="/admin/dishes">Menu</a></li>
            <li><a href="/admin/orders">Orders</a></li>
            <li><a href="/admin/storage">Storage</a></li>
        </ul>
    </nav>
</header>

<div class="employeeCentralPanel">
    <c:if test="${doesItAlreadyExist == false}">
    <form action="/admin/employees" method="post">
        <table>
            <tr>
                <td>Name</td>
                <td><input autocomplete="off" type="text" name="employeeName" value="${employee.firstName}" required></td>
            </tr>

            <tr>
                <td>Position</td>
                <td>
                    <select name="employeePosition">
                        <option>Waiter</option>
                        <option>Cook</option>
                        <option>Administrator</option>
                    </select>
                </td>
            </tr>

        </table>
        <div class="employeeInformationSubmitButton">
            <button>Submit</button>
        </div>
    </form>
    </c:if>

    <c:if test="${doesItAlreadyExist == true}">
    <form action="/admin/updatedEmployeeId=${employee.id}" method="post">
        <table>
            <tr>
                <td>Name</td>
                <td><input autocomplete="off" type="text" name="employeeName" value="${employee.firstName}"></td>
            </tr>

            <tr>
                <td>Position</td>
                <td>
                    <select name="employeePosition">
                        <option>Waiter</option>
                        <option>Cook</option>
                        <option>Administrator</option>
                    </select>
                </td>
            </tr>


        </table>
        <div class="employeeInformationSubmitButton">
        <button>Submit</button>
        </div>
    </form>
    </c:if>
</div>
</body>
</html>
