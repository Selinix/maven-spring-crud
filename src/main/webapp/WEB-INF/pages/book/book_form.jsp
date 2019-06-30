<%@ page contentType="text/html;charset=windows-1251" pageEncoding="windows-1251" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri ="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Book form</title>
</head>
<body>
<spring:url value="/save" var="saveURL"/>
<form:form modelAttribute="bookForm" method="POST" action="${saveURL}">
    <form:hidden path="id"/>
<table>
    <tr>
        <td>Name</td>
        <td><form:input path="name"/>
    </tr>
    <tr>
        <td>Autor</td>
        <td><form:input path="autor"/>
    </tr>
    <tr>
        <td>Price</td>
        <td><form:input path="price"/>
    </tr>
    <tr>
        <td></td>
        <td><button type="submit">Save</button>
    </tr>
</table>

</form:form>

</body>
</html>
