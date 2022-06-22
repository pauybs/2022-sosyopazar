package com.mcavlak.sosyobazaar.security;

import com.mcavlak.sosyobazaar.enums.Role;
import com.mcavlak.sosyobazaar.managers.UserDetailsManager;
import com.mcavlak.sosyobazaar.models.CustomUserDetails;
import com.mcavlak.sosyobazaar.models.entities.users.Customer;
import com.mcavlak.sosyobazaar.models.entities.users.Seller;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@Transactional
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @Autowired
    private UserDetailsManager userDetailsManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Transactional
    private void doWork(HttpServletRequest request) {

        //session already authenticated
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        //header empty
        String header = request.getHeader(HEADER_STRING);
        if (header == null) {
            return;
        }

        //token prefix control
        if (!header.startsWith(TOKEN_PREFIX)) {
            return;
        }

        //extract token
        String authToken = header.replace(TOKEN_PREFIX, "");
        JwtToken jwtToken = JwtToken.parse(authToken);

        //load userdetails
        String username = jwtToken.getClaims().get(JwtTokenUtil.KEY_USERNAME, String.class);

        CustomUserDetails userDetails = userDetailsManager.loadUserByUsername(username);

        //userdetails not found
        if (userDetails == null) {
            return;
        }

        if(userDetails.getUser().getRole() == Role.ROLE_CUSTOMER){
            Customer c = (Customer) userDetails.getUser();
            c.getFollows().size();
        }
        if(userDetails.getUser().getRole() == Role.ROLE_SELLER){
            Seller s = (Seller) userDetails.getUser();
            s.getFollowers().size();
        }

        //check validity
        if (jwtTokenUtil.isValid(jwtToken, userDetails)) {
            //set security
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            doWork(request);
        } catch (UsernameNotFoundException | SignatureException | MalformedJwtException | ExpiredJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Yetkisiz Giriş");
        }

        filterChain.doFilter(request, response);

    }


}
