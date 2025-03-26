package me.dhiren9939.jwtauth.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.dhiren9939.jwtauth.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class PublicRouteSkipper {
    private final static AntPathRequestMatcher[] PUBLIC_ROUTE_MATCHERS = Arrays.stream(SecurityConfig.PUBLIC_ROUTES)
            .map(AntPathRequestMatcher::new)
            .toArray(AntPathRequestMatcher[]::new);

    public boolean ifPublicSkipFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        for (AntPathRequestMatcher matcher : PUBLIC_ROUTE_MATCHERS) {
            if (matcher.matches(request)) {
                // no need to do anything with security context as this are
                // public paths those filters will be skipped
                filterChain.doFilter(request, response);
                return true;
            }
        }
        return false;
    }
}
