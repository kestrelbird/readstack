<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<c:forEach items="${requestScope.discoveries}" var="discovery">
    <article class="discovery">
        <h2 class="discovery-header"><c:out value="${discovery.title}"/></h2>
        <p class="discovery-details">Dodane przez: User, ${discovery.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
        <a href="<c:out value="${discovery.url}"/>" class="discovery-link" target="_blank"><c:out value="${discovery.url}"/></a>
        <p><c:out value="${discovery.description}"/></p>
        <section class="discovery-bar">
            <a href="#" class="discovery-link upvote">
                <i class="fas fa-arrow-alt-circle-up discovery-upvote"></i>
            </a>
            <p class="discovery-votes">32</p>
            <a href="#" class="discovery-link downvote">
                <i class="fas fa-arrow-alt-circle-down discovery-downvote"></i>
            </a>
        </section>
    </article>
</c:forEach>