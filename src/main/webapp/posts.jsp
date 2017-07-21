<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>First blog :)</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="max_width_400">
    <c:forEach items="${posts}" var="post">
        <h3>${post.title}</h3>
        <p>${formatter.format(post.postedDate)}</p>
        <p>
            ${post.postedText}
        </p>
        <a href="posts/${post.postId}"> Continue</a>
        <br/>
    </c:forEach>
</div>
<br/>
<form action="/posts" method="get">
    <input type="submit" name="create" value="Create new post"/>
</form>
</body>
</html>