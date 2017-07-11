<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Post</title>
</head>
<body>
<h2 align="center"><c:out value="${post.title}"/></h2>
<h4 align="center"><c:out value="${formatter.format(post.postedDate)}"/></h4>
<p>
    <c:out value="${post.postedText}"/>
</p>
<form align="center" action="/posts/${post.postId}/delete" method="post">
    <input type="submit" name="delete" value="Delete"/>
</form>
<form align="center" action="/posts/${post.postId}/edit" method="get">
    <input type="submit" name="edit" value="Edit"/>
</form>
</body>
</html>