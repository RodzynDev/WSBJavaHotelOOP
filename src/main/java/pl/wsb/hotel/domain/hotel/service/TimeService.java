package pl.wsb.hotel.domain.hotel.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeService extends AbstractService {
    protected TimeService(String name) {
        super("Time service", false);
    }

    @Override
    void orderService() {
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println(formatterDateTime.format(currentTime));
    }

    @Override
    void checkServiceAvailability(LocalDateTime orderTime) {
        System.out.println("Time service is always available.");
    }
}
