<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>${requestScope.category.categoryName} - ReadStack</title>
    <%@include file="../segments/stylesheets.jsp"%>
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jsp"%>

    <main>
        <h1>${requestScope.category.categoryName}</h1>
        <p>${requestScope.category.categoryDescription}</p>
        <%@include file="../segments/discovery-list.jsp"%>
    </main>
    <%@include file="../segments/footer.jsp"%>
</div>
</body>
</html>
