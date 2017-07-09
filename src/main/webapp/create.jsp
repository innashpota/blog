<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<h2 align="center">Create new post</h2>
<form action="/posts" method="post">
    <h4>Title:</h4>
    <input type="text" name="title" border="1" cellspacing="0" cellpadding="2" style="width: 600px;">
    <h4>Context:</h4>
    <textarea name="context" border="1" cellspacing="0" cellpadding="2" style="width: 600px; height: 200px;">
    </textarea>
    <br/>
    <input type="submit" name="add" value="Add post" align="center"/>
</form>
</body>
</html>