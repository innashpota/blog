<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2 align="center">Edit post <c:out value="\"${post.title}\""/></h2>
<form action="/posts/${post.postId}/edit" method="get">
    <h4>Title:</h4>
    <input type="text" name="title" value="${post.title}" border="1" cellspacing="0" cellpadding="2" style="width: 600px;"/>
    <h4>Context:</h4>
    <textarea name="context" border="1" cellspacing="0" cellpadding="2" style="width: 600px; height: 200px;">
        <c:out value="${post.postedText}"/>
    </textarea>
    <br/>
    <input type="submit" name="edit" value="Edit" align="center"/>
</form>
</body>
</html>