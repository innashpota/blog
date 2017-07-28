<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Page Not Found</h1>
<h3>
    <c:if test="${not empty message}">
        <c:out value="${message}"/>
    </c:if>
    <c:if test="${empty message}">
        <c:out value="Internal error occurred. Please contact administrator."/>
    </c:if>
</h3>
<a href="" onclick="history.back()">Back to Previous Page</a>
</body>
</html>