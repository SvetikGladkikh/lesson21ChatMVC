<%--
  Created by IntelliJ IDEA.
  User: exodus1
  Date: 17.01.2021
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Добро пожаловать в чат!</title>
</head>
<body>
    <p>Добро пожаловать в чат, <b>${user}</b>! <a href="\logout">Выйти</a></p>

    <%  Boolean isAdmin = (Boolean) request.getAttribute("isAdmin");
        if (isAdmin) {
    %>
            <p><a href="\blockedUsers">Управление заблокированными пользователями</a></p>
    <%
        }
    %>

    <p><a href="\newMessage">Новое сообщение</a></p>

    <table border="1" cellpadding="5" cellspacing="1" >
        <c:forEach items="${messages}" var="message" >
            <tr>
                <td>
                    <table border="1" cellpadding="5" cellspacing="1" >
                        <td>${message.date}</td>
                        <td><b>${message.user}</b></td>
                    </table>
                    <p>${message.text}</p>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
