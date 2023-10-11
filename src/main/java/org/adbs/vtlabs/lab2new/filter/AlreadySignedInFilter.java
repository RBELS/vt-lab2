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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@WebFilter(urlPatterns = {"/login", "/register"})
public class AlreadySignedInFilter implements Filter {
    private ServletContext servletContext;
    private AuthorityService authorityService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        Optional<Cookie> authCookie = Stream.of(req.getCookies())
                .filter(cookie -> cookie.getName().equals("auth-token"))
                .findFirst();
        String token = authCookie.isPresent() && StringUtils.isNotEmpty(authCookie.get().getName()) ? authCookie.get().getValue() : null;

        if (Objects.isNull(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        try {
            EStorePrincipal principal = authorityService.verifyUserJwt(token);
            res.sendRedirect(servletContext.getContextPath()+"/home");
            return;
        } catch (InvalidTokenException e) {
            authCookie.get().setMaxAge(0);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
        authorityService = AuthorityService.getInstance();
        this.servletContext.log("AlreadySignedInFilter initialized");
    }
}
