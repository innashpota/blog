<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>Page Not Found</h1>
    <h3><c:out value="${message}"/></h3>
    <a href="" onclick="history.back()">Back to Previous Page</a>
</body>
</html>
