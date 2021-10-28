<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Печатные изделия</title>
</head>
<body>
<table>
    <th>Название</th>
    <th>Автор</th>
    <th>Дата выпуска</th>
    <th>Издательство</th>
    <th>Тип</th>
    <c:forEach var="printed_product" items="${requestScope.products}">
        <tr>
            <td><c:out value="${printed_product.name}"/></td>
            <td><c:out value="${printed_product.author.name}"/></td>
            <td><c:out value="${printed_product.dateOfIssue}"/></td>
            <td><c:out value="${printed_product.publishOffice.name}"/></td>
            <td><c:out value="${printed_product.type.name}"/></td>
        </tr>
    </c:forEach>
</table>

<a style="display: block" href="../index.html">На главную</a>
</body>
</html>
