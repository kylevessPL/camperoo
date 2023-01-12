package pl.piasta.camperoo.security.domain;

import java.time.Instant;

public interface AuthenticationTokenCleanupScheduler {
    void scheduleExpiredVerificationTokenCleanup(Long id, Instant expirationDate);
}
