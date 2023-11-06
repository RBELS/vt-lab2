package org.adbs.vtlabs.lab2new.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.adbs.vtlabs.lab2new.exception.InvalidTokenException;
import org.adbs.vtlabs.lab2new.security.EStorePrincipal;
import org.adbs.vtlabs.lab2new.service.AuthorityService;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@WebFilter(urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
    private ServletContext servletContext;
    private AuthorityService authorityService;

    private static final Set<String> ALLOWED_URLS = Set.of("/login", "/register");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", "");
        if (ALLOWED_URLS.contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (Objects.isNull(req.getCookies())) {
            servletContext.log("No `Authorization` header was provided.");
            res.setStatus(401);
            res.sendRedirect(servletContext.getContextPath()+"/login");
            return;
        }
        Optional<String> authCookie = Stream.of(req.getCookies())
                .filter(cookie -> cookie.getName().equals("auth-token"))
                .findFirst()
                .map(Cookie::getValue);
        String token = authCookie.isPresent() && StringUtils.isNotEmpty(authCookie.get()) ? authCookie.get() : null;

        if (Objects.isNull(token)) {
            servletContext.log("No `Authorization` header was provided.");
            res.setStatus(401);
            res.sendRedirect(servletContext.getContextPath()+"/login");
            return;
        }

        try {
            EStorePrincipal principal = authorityService.verifyUserJwt(token);
        } catch (InvalidTokenException e) {
            servletContext.log("Cannot verify token.");
            res.setStatus(400);
            res.sendRedirect(servletContext.getContextPath()+"/login");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
        authorityService = AuthorityService.getInstance();
        this.servletContext.log("Authentication initialized");
    }
}
