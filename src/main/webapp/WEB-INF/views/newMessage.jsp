<%--
  Created by IntelliJ IDEA.
  User: exodus1
  Date: 17.01.2021
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добро пожаловать в чат!</title>
    <form method="post">
        <p>Новое сообщение от <b>${user}</b>:</p>
        <textarea rows="10" cols="45" name="messageText"></textarea><br/>
        <input type="submit" value="Отправить">
    </form>
</head>
<body>

</body>
</html>
