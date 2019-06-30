

<%--
  Created by IntelliJ IDEA.
  User: Никита
  Date: 28.06.2019
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;windows-1251" pageEncoding="windows-1251" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri ="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Book page</title>
</head>
<body>

<spring:url value="/add" var="addURL" />
<a href="${addURL}">Add Book</a>

<h1>Book list</h1>
<table width="100%" border="1">
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>AUTOR</th>
        <th>PRICE</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach items="${listBook}" var="book">
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.autor}</td>
            <td>${book.price}</td>
            <td>
                <spring:url value ="/update/${book.id}" var = "updateURL" />
                <a href="${updateURL}">Update</a>
            </td>
            <td>
                <spring:url value ="/delete/${book.id}" var = "deleteURL" />
                <a href="${deleteURL}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
