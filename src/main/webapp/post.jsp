<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Post</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<h2>
    <c:out value="${post.title}"/>
</h2>
<h4>
    <c:out value="${formatter.format(post.postedDate)}"/>
</h4>
<p class="format_post">
    <c:out value="${post.postedText}"/>
</p>
<div class="max_width_100">
    <form class="buttons_center" action="/posts/${post.postId}/delete" method="post">
        <input type="submit" name="delete" value="Delete"/>
    </form>
    <form class="buttons_center" action="/posts/${post.postId}/edit" method="get">
        <input type="submit" name="edit" value="Edit"/>
    </form>
</div>
</body>
</html>