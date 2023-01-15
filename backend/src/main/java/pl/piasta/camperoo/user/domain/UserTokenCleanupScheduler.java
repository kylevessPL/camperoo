package pl.piasta.camperoo.user.domain;

import java.time.Instant;

public interface UserTokenCleanupScheduler {
    void scheduleExpiredVerificationTokenCleanup(Long id, Instant expirationDate);
}
