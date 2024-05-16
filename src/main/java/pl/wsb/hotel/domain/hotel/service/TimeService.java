package pl.wsb.hotel.domain.hotel.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeService extends AbstractService {

    public TimeService() {
        super("Time service", false);
    }

    @Override
    public void orderService() {
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("The time service is ordered for " + formatterDateTime.format(currentTime));
    }

    @Override
    void checkServiceAvailability(LocalDateTime orderTime) {
        System.out.println("Time service is always available.");
    }

    @Override
    public void getFullInfoInPL() {
        System.out.println("ta klasa odpowiada za pokazywanie czasu");
    }
}
