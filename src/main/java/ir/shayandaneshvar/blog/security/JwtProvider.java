package ir.shayandaneshvar.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;

@Component
public class JwtProvider {
    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(Authentication auth) {
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        return Jwts.builder().setSubject(principal.getUsername())
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String jwt) {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(jwt);
        return true;
    }

    public String getUsername(String jwt) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJwt(jwt).getBody();
        return claims.getSubject();
    }
}
