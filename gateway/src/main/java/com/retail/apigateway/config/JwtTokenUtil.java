package com.retail.apigateway.config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.retail.apigateway.model.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static com.retail.apigateway.model.Constants.SIGNING_KEY;
import static com.retail.apigateway.model.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;

@Component
public class JwtTokenUtil implements Serializable {

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Account user) {
        return doGenerateToken(user);
    }

    private String doGenerateToken(Account user) {

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("authorities", new ArrayList<String>() {{add(user.getRole().name());}});

        String t = null;
        try{
            t = Jwts.builder()
                    .setClaims(claims)
                    .setIssuer("http://devglan.com")
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                    .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                    .compact();
        } catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

}
