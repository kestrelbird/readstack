<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>ReadStack</title>
    <%@include file="../segments/stylesheets.jsp"%>    
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jsp"%>
    <aside class="categories">
        <ul>
        <c:forEach var="category" items="${requestScope.categories}">
            <li><a href="${pageContext.request.contextPath.concat('/category?id=').concat(category.id)}">${category.categoryName}</a></li>
        </c:forEach>
        </ul>
    </aside>
    <main>
        <%@include file="../segments/discovery-list.jsp"%>
    </main>
    <%@include file="../segments/footer.jsp"%>
</div>
</body>
</html>