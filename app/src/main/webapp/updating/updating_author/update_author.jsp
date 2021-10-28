<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Обновление имени автора</title>
</head>
<body>
  <form action="${pageContext.request.contextPath}/update_author_servlet" method="post">
    Введите имя автора печатного изделия:
    <input style="display: block" name="old_author_name" type="text">
    Введите новое имя автора печатного изделия:
    <input style="display: block" name="new_author_name" type="text">
    <input style="display: block" name="Обновить" type="submit">
    <a style="display: block" href="${pageContext.request.contextPath}/update">Назад</a>
  </form>
</body>
</html>
