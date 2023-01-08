package pl.piasta.camperoo.infrastructure.scheduling;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
class SchedulingService {
    private final OrderJobManager orderJobManager;

    @Async
    @Scheduled(cron = "${app.scheduling.expired-payments-cleanup-cron}")
    public void cleanupExpiredPayments() {
        orderJobManager.cancelExpiredPayments();
    }

    @Async
    @Scheduled(cron = "${app.scheduling.unpaid-orders-cleanup-cron}")
    public void cleanupUnpaidOrders() {
        orderJobManager.cancelUnpaidOrders();
    }
}
