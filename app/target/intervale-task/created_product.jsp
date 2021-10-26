<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Созданное печатное изделие</title>
</head>
<body>
    <%= "Создано изделие: " + request.getAttribute("created_printed_product")%>
    <a style="display: block" href="index.html">На главную</a>
</body>
</html>
