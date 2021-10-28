<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Обновление названия издательства</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/update_publish_office_servlet" method="post">
        Введите название издательства
        <input style="display:block" name="old_publish_office_name" type="text">
        Введите новое название издательства
        <input style="display: block" name="new_publish_office_name" type="text">
        <input style="display: block" name="Обновить" type="submit">
        <a style="display: block" href="${pageContext.request.contextPath}/update">Назад</a>
    </form>
</body>
</html>
