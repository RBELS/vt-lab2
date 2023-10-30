<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <!-- Подключаем CSS Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <!-- Подключаем собственные стили для центрирования списка -->
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
        }
        .book-list {
            max-width: 800px;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            text-align: center;
        }
        a {
            text-decoration: none;
            color: unset;
        }
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/home">
    <h2 class="mb-4">Home</h2>
</a>
<div class="container book-list">
    <h3>${error.message}</h3>
</div>
<!-- Подключаем JS Bootstrap (необходим для некоторых компонентов) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
