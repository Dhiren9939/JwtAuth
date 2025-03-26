package me.dhiren9939.jwtauth.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.dhiren9939.jwtauth.exceptions.InvalidJwtException;
import me.dhiren9939.jwtauth.service.auth.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    private final PublicRouteSkipper routeSkipper;

    public JwtAuthenticationFilter(JwtService jwtService, PublicRouteSkipper routeSkipper) {
        this.jwtService = jwtService;
       this.routeSkipper = routeSkipper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(routeSkipper.ifPublicSkipFilter(request,response,filterChain))
            return;

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidJwtException("invalid token format");
        }

        String token = authHeader.substring(7);

        try {
            String email = jwtService.getAllClaims(token).getSubject();

            if (!jwtService.isTokenValid(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "expired token");
                return;
            }

            // need to add this or else other filters will block this request
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw new InvalidJwtException("invalid token");
        }
    }
}
