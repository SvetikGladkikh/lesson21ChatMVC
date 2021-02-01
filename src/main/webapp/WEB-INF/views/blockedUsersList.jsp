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
    <p>Добро пожаловать в управление заблокированными пользователями, <b>${user}</b>! <a href="\logout">Выйти</a></p>

    <p><a href="\messages">Перейти в чат</a></p>

    <p>Перечень пользователей:</p>
    <table>
        <tr>
            <td>
                <a href="\blockUser">Заблокировать</a>
            </td>
            <td>
                <a href="\unblockUser">Разблокировать</a>
            </td>
        </tr>
    </table>

    <table border="1" cellpadding="5" cellspacing="1" >
        <c:forEach items="${blockedUsers}" var="blockedUser" >
            <tr>
                <td>
                    ${blockedUser}
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
