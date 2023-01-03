<%--
  Created by IntelliJ IDEA.
  User: Mateo
  Date: 02.12.2022
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dodaj znalezisko</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/add-discovery.css">
    <%@include file="../segments/stylesheets.jsp"%>
</head>
<body>
<div class="container">
<%@include file="../segments/header.jsp"%>

<form action="${pageContext.request.contextPath}/discovery/add" method="post" class="discovery-form">
    <h2 class="discovery-form-title">Dodaj nowe znalezisko</h2>
    <input name="title" placeholder="Tytuł" required>
    <input name="url" placeholder="URL" required>
    <select name="categoryId">
        <c:forEach var="category" items="${requestScope.categories}">
            <option value="${category.id}">${category.categoryName}</option>
        </c:forEach>
    </select>
    <textarea name="description" placeholder="Opis"></textarea>
    <button class="discovery-form-button">Dodaj</button>
</form>

<%@include file="../segments/footer.jsp"%>
</div>
</body>
</html>
