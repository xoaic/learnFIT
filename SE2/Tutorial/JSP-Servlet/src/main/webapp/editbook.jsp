<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xoaic
  Date: 19/02/2022
  Time: 9:47 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container col-md-6 mt-3">
    <h2 class="text-success text-center">Book List</h2>
    <table class="table table-bordered text-center mt-3">
        <tr>
            <th>Book ID</th>
            <th>Book Title</th>
            <th>Book Author</th>
            <th>Book Price</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td><c:out value="${book.id}"/></td>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.price}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
