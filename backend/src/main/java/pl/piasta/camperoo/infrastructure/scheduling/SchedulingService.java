package pl.piasta.camperoo.infrastructure.scheduling;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import pl.piasta.camperoo.security.domain.AuthenticationTokenCleanupScheduler;
import pl.piasta.camperoo.user.domain.UserTokenCleanupScheduler;

import java.time.Instant;

@Async
@RequiredArgsConstructor
class SchedulingService implements AuthenticationTokenCleanupScheduler, UserTokenCleanupScheduler, AsyncScheduler {
    private final TaskScheduler taskScheduler;
    private final OrderJobManager orderJobManager;

    public void scheduleExpiredVerificationTokenCleanup(Long id, Instant expirationDate) {
        taskScheduler.schedule(() -> orderJobManager.deleteExpiredVerificationToken(id), expirationDate);
    }

    @Scheduled(cron = "${app.scheduling.expired-payments-cleanup-cron}")
    public void cleanupExpiredPayments() {
        orderJobManager.cancelExpiredPayments();
    }

    @Scheduled(cron = "${app.scheduling.unpaid-orders-cleanup-cron}")
    public void cleanupUnpaidOrders() {
        orderJobManager.cancelUnpaidOrders();
    }
}
