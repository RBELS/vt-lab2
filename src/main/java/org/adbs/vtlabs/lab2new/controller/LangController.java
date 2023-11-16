package org.adbs.vtlabs.lab2new.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LangController {
    public static final Map<String, Map<String, String>> langMap;
    static {
        langMap = new HashMap<>();
        Map<String, String> enMap = new HashMap<>();
        enMap.put("title", "Books Market");
        enMap.put("loginHeader", "Log In");
        enMap.put("username", "username");
        enMap.put("password", "password");
        enMap.put("loginBtn", "Log In");
        enMap.put("createAccount", "Create Account");
        enMap.put("register", "Register");
        enMap.put("author", "Author");
        enMap.put("price", "Price");
        enMap.put("description", "Description");
        enMap.put("addBook", "Add a book");
        enMap.put("removeBook", "Remove book");
        enMap.put("logout", "Logout");
        enMap.put("home", "Home");
        enMap.put("name", "Name");
        enMap.put("submit", "Submit");
        enMap.put("editBook", "Edit Book");

        Map<String, String> ruMap = new HashMap<>();
        ruMap.put("title", "Книжный магазин");
        ruMap.put("loginHeader", "Войти в учетную запись");
        ruMap.put("username", "Имя пользователя");
        ruMap.put("password", "Пароль");
        ruMap.put("loginBtn", "Войти");
        ruMap.put("createAccount", "Создать аккаунт");
        ruMap.put("register", "Зарегистрироваться");
        ruMap.put("author", "Автор");
        ruMap.put("price", "Цена");
        ruMap.put("description", "Описание");
        ruMap.put("addBook", "Добавить книгу");
        ruMap.put("removeBook", "Удалить книгу");
        ruMap.put("logout", "Выйти из аккаунта");
        ruMap.put("home", "На домашнюю страницу");
        ruMap.put("name", "Название");
        ruMap.put("submit", "Подтвердить");
        ruMap.put("editBook", "Редактировать");

        langMap.put("en", enMap);
        langMap.put("ru", ruMap);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("value");
        if (Objects.nonNull(langMap.get(lang))) {
            resp.addCookie(new Cookie("lang", lang));
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}
