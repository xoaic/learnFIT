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
        <tr>
            <th><input type="text" id="nid" name="nid"></th>
            <th><input type="text" id="ntitle" name="ntitle"></th>
            <th><input type="text" id="nauthor" name="nauthor"></th>
            <th><input type="text" id="nprice" name="nprice"></th>
        </tr>
    </table>
</div>
</body>
</html>
