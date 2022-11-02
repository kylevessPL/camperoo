package pl.piasta.camperoo.infrastructure.auth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import pl.piasta.camperoo.security.TokenPrincipal;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
class JwtProvider {
    private static final String ID = "id";
    private static final String ROLES = "roles";

    private final Key secret;
    private final long validMinutes;

    public JwtProvider(String jwtSecret, long jwtValidMinutes) {
        this.secret = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.validMinutes = jwtValidMinutes;
    }

    public String createToken(TokenPrincipal tokenPrincipal, Instant ofTime) {
        var issuanceDate = ofTime.truncatedTo(ChronoUnit.SECONDS);
        var expirationDate = issuanceDate.plus(validMinutes, ChronoUnit.MINUTES);
        return Jwts.builder()
                .setSubject(tokenPrincipal.email())
                .claim(ID, tokenPrincipal.id())
                .claim(ROLES, tokenPrincipal.roles())
                .setIssuedAt(Date.from(issuanceDate))
                .setExpiration(Date.from(expirationDate))
                .signWith(secret, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            log.info(String.format("Valid authentication token: %s", token));
            return true;
        } catch (JwtException ex) {
            log.warn(String.format("Invalid authentication token: %s => %s", token, ex.getMessage()));
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public TokenPrincipal extractPrincipal(String token) {
        var body = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return new TokenPrincipal(body.get(ID, Long.class), body.getSubject(),
                Set.copyOf((List<String>) body.get(ROLES)));
    }

    public Instant extractExpirationTime(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .toInstant();
    }
}
