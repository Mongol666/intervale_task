<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>

    <title>Создание нового изделия</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/create_product_servlet" method="post">
        Введите название:
        <input style="display: block" name="name" type="text">
        Введите автора:
        <input style="display: block" name="author_name" type="text">
        Введите название издательства:
        <input style="display: block" name="publish_office_name" type="text">
        Введите тип изделия:
        <input style="display: block" name="type_name" type="text">
        Введите год выхода:
        <input style="display: block" name="year_of_issue" type="text">
        Введите месяц выхода:
        <input style="display: block" name="month_of_issue" type="text">
        Введите день выхода:
        <input style="display: block" name="day_of_issue" type="text">
        <input style="display: block" name="Создать" type="submit">

        <a style="display: block" href="../index.html">Назад</a>
    </form>
</body>
</html>
