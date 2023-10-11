<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
      width: 100%;
      display: inline-block;
    }
  </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/"><h2 class="mb-4">Home</h2></a>
<div class="container book-list">
    <div class="card book-card">
      <div class="card-body">
        <h5 class="card-title">${book.name}</h5>
        <p class="card-text">Author: ${book.author}</p>
        <p class="card-text">Price: ${book.price}</p>
        <h5 class="mb-1">Description:</h5>
        <p class="card-text">${book.description}</p>
      </div>
    </div>
</div>
<div class="mt-3">
  <a onclick="sendDelete('${pageContext.request.contextPath}', ${book.bookId})" class="btn btn-danger">Remove</a>
</div>
<!-- Подключаем JS Bootstrap (необходим для некоторых компонентов) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"></script>
<script src="resources/js/book.js"></script>
</body>
</html>