<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<h2>Create new post</h2>
<form action="/posts" method="post">
    <h4>Title:</h4>
    <input class="text_title" type="text" name="title" required/>
    <h4>Context:</h4>
    <textarea class="text_title" name="context" required>
    </textarea>
    <br/>
    <br/>
    <input type="submit" name="add" value="Add post" align="center"/>
</form>
</body>
</html>