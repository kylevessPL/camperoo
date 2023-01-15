package pl.piasta.camperoo.infrastructure.scheduling;

import java.time.Instant;

interface AsyncScheduler {
    void scheduleExpiredVerificationTokenCleanup(Long id, Instant expirationDate);

    void cleanupExpiredPayments();

    void cleanupUnpaidOrders();
}
