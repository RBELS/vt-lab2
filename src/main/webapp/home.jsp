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
        }
        a {
            text-decoration: none;
            color: unset;
        }
        .book-card {
            display: inline-block;
            width: 220px;
            margin: 20px 15px;
        }
    </style>
</head>
<body>
<h2 class="mb-4">Books for Sale</h2>
<div class="container book-list">
    <c:forEach var="book" items="${books}">
        <div class="card book-card">
            <a href="${pageContext.request.contextPath}/book?id=${book.bookId}">
                <div class="card-body">
                    <h5 class="card-title">${book.name}</h5>
                    <p class="card-text">Author: ${book.author}</p>
                    <p class="card-text">Price: ${book.price}</p>
                </div>
            </a>
        </div>
    </c:forEach>
</div>
<div class="mt-3">
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-secondary">Logout</a>
    <a href="${pageContext.request.contextPath}/create" class="btn btn-secondary">Create</a>
</div>
<!-- Подключаем JS Bootstrap (необходим для некоторых компонентов) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
</body>
</html>


