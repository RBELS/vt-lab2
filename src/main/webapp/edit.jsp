<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
  <!-- Подключаем CSS Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <!-- Подключаем собственные стили для центрирования формы -->
  <style>
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      background-color: #f0f0f0;
    }
    .login-form {
      width: 600px;
      max-width: 800px;
      padding: 20px;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }
  </style>
</head>
<body>
<div class="login-form">
  <h2 class="mb-4">${lang.editBook}</h2>
  <form action="${pageContext.request.contextPath}/edit?id=${book.bookId}" method="post">
    <div class="mb-3">
      <label for="name" class="form-label">${lang.name}</label>
      <input value="${book.name}" type="text" class="form-control" id="name" name="name" required>
    </div>
    <div class="mb-3">
      <label for="author" class="form-label">${lang.author}</label>
      <input value="${book.author}" type="text" class="form-control" id="author" name="author" required>
    </div>
    <div class="mb-3">
      <label for="price" class="form-label">${lang.price}</label>
      <input value="${book.price}" type="text" class="form-control" id="price" name="price" required>
    </div>
    <div class="mb-3">
      <label for="description" class="form-label">${lang.description}</label>
      <textarea type="text" class="form-control" id="description" name="description" required>${book.description}</textarea>
    </div>
    <button type="submit" class="btn btn-primary">${lang.submit}</button>
  </form>
</div>
<!-- Подключаем JS Bootstrap (необходим для некоторых компонентов) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>

