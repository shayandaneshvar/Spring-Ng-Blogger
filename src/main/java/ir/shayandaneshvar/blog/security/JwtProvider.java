package ir.shayandaneshvar.blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import ir.shayandaneshvar.blog.exceptions.SpringBlogException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@Component
public class JwtProvider {
    private KeyStore key;

    @PostConstruct
    public void init() {
        try {
            key = KeyStore.getInstance("JKS");
            key.load(getClass().getResourceAsStream("/springblog.jks"),
                    "123456".toCharArray());
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException |
                CertificateException e) {
            e.printStackTrace();
            throw new SpringBlogException("An error occurred while loading " +
                    "the keystore");
        }
    }

    public String generateToken(Authentication auth) {
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        return Jwts.builder().setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) key.getKey("springblog",
                    "123456".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException |
                UnrecoverableKeyException e) {
            throw new SpringBlogException("Exception occurred while " +
                    "retrieving the private key from the keystore", e);
        }
    }

    public boolean validateToken(String jwt) {
        Jwts.parserBuilder().setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return key.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new SpringBlogException("Exception occurred while " +
                    "retrieving the public key from the keystore", e);
        }
    }

    public String getUsername(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getPublicKey()).build()
                .parseClaimsJws(jwt).getBody();
        return claims.getSubject();
    }
}
