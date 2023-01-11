<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<c:forEach items="${requestScope.discoveries}" var="discovery">
    <article class="discovery">
        <h2 class="discovery-header"><c:out value="${discovery.title}"/></h2>
        <p class="discovery-details">Dodane przez: ${discovery.author}, ${discovery.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
        <a href="<c:out value="${discovery.url}"/>" class="discovery-link" target="_blank"><c:out value="${discovery.url}"/></a>
        <p><c:out value="${discovery.description}"/></p>
        <section class="discovery-bar">
            <a href="${pageContext.request.contextPath.concat('/discovery/vote?id=').concat(discovery.id).concat('&type=UP')}"  class="discovery-link upvote">
                <i class="fas fa-arrow-alt-circle-up discovery-upvote"></i>
            </a>
            <p class="discovery-votes">${discovery.voteCount}</p>
            <a href="${pageContext.request.contextPath.concat('/discovery/vote?id=').concat(discovery.id).concat('&type=DOWN')}" class="discovery-link downvote">
                <i class="fas fa-arrow-alt-circle-down discovery-downvote"></i>
            </a>
        </section>
    </article>
</c:forEach>
<a href="${pageContext.request.contextPath}/discovery/add" class="discovery-add-button">
    <i class="fa fa-plus"></i>
</a>