<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Обновление названия печатного изделия</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/update_printed_product_name_servlet" method="post">
    Введите название печатного изделия:
    <input style="display: block" name="old_printed_product_name" type="text">
    Введите новое название печатного изделия:
    <input style="display: block" name="new_printed_product_name" type="text">
    <input style="display: block" name="Обновить" type="submit">
    <a style="display: block" href="${pageContext.request.contextPath}/update">Назад</a>
</form>
</body>
</html>
