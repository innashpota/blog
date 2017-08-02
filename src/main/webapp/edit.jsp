<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edit</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<h2>Edit post <c:out value="\"${post.title}\""/></h2>
<form action="/posts/${post.postId}/edit" method="post">
    <h4>Title:</h4>
    <input class="text_title" type="text" name="title" value="${post.title}" required/>
    <h4>Context:</h4>
    <textarea class="text_title" name="context" required>
        <c:out value="${post.postedText}"/>
    </textarea>
    <br/>
    <br/>
    <input class="value_center" type="submit" name="save" value="Save"/>
</form>
</body>
</html>