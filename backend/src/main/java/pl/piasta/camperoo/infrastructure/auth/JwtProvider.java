package pl.piasta.camperoo.infrastructure.auth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import pl.piasta.camperoo.security.TokenPrincipal;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static de.xn__ho_hia.quality.suppression.CompilerWarnings.UNCHECKED;

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

    public String createToken(TokenPrincipal tokenPrincipal) {
        var issuanceDate = Instant.now().truncatedTo(ChronoUnit.SECONDS);
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
            logger.debug("Valid authentication token: " + token);
            return true;
        } catch (JwtException ex) {
            logger.warn("Invalid authentication token: %s => %s".formatted(token, ex.getMessage()));
            return false;
        }
    }

    @SuppressWarnings(UNCHECKED)
    public TokenPrincipal extractPrincipal(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return new TokenPrincipal(claims.get(ID, Long.class), claims.getSubject(),
                Set.copyOf((List<String>) claims.get(ROLES)));
    }

    public Pair<Instant, Instant> extractDates(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Pair.of(claims.getIssuedAt().toInstant(), claims.getExpiration().toInstant());
    }
}
