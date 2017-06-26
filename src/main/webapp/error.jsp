<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
    <h1><c:out value="${message}"/></h1>
    <a href="" onclick="history.back()">Back to Previous Page</a>
</body>
</html>
