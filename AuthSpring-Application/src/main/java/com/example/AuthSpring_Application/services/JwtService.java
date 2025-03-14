package com.example.AuthSpring_Application.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService  {

    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    public String extractUserEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     *  <p style="font-size:11px">extractClaim()<p/><p>-Извлекает нужное значение из JWT.
     *  Главной целью является то что, при входе в систему проверки данных к примеру валидности
     * payload, signature и также других данных из body. Мы будем пользоваться именно
     * этим методом.<p/>
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)                     // объект-каркас для настройки payload
                .setSubject(userDetails.getUsername())      // методы для кастомизации каркаса
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();                                 //Преобразуем токен в String type
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userEmail = extractUserEmail(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
         return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /** <p style="font-size:11px"> extractAllClaims()<p/><p>За свою структуру берет настройку парсинга jwt токена. Извлечение из него
     * payload, валидность signature. Т.е его основная работа дешифровать токен, проверить валидность
     * signature и предоставление дешифрованной формы структуры для дальнейшего извлечения его через
     * метод extractClaim()<p>
     *
     */
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
