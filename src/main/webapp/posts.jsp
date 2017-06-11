<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>All posts</title>
</head>
<h2 align="center">Post from Postgresql Database</h2>
<table border="1" cellspacing="0" cellpadding="2" align="center">
    <tr>
        <td>Data post</td>
        <td>Title</td>
    </tr>

    <c:forEach items="${posts}" var="post" varStatus="loop">
        <tr>
            <td>${post.postedDate}</td>
            <td><a href="post/${loop.index}"> ${post.title}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
