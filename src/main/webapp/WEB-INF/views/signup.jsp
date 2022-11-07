<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Zarejestruj się</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">

    <%@include file="../segments/stylesheets.jsp"%>
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jsp"%>

    <form action="${pageContext.request.contextPath}/signup" method="post" class="user-form">
       <h2 class="user-form-title">Zarejestruj się na ReadStack</h2>
        <input name="username" placeholder="Nazwa użytkownika" required>
        <input name="email" placeholder="Adres email" required>
        <input name="password" placeholder="Hasło" type="password" required>
        <button class="user-form-button">Zarejestruj się</button>
    </form>

    <%@include file="../segments/footer.jsp"%>>
</div>
</body>
</html>