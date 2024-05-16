package pl.wsb.hotel.domain.hotel.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LuggageService extends AbstractService {
    public LuggageService() {
        super( "Luggage service", true);
    }

    @Override
    public void orderService() {
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime nextHour = currentTime.plusHours(1).truncatedTo(java.time.temporal.ChronoUnit.HOURS);
        System.out.println("The luggage service is ordered for " + formatterDateTime.format(nextHour));
    }

    @Override
    public void checkServiceAvailability(LocalDateTime orderTime) {
        if (!isForPremiumClients())
            System.out.println("We are sorry. You are not a premium client.");
        else
            System.out.println("Szymon Ciepliński coś wymyśli hehehe");
            System.out.println(".... Masz racje wymyśliłem");
            String s="";
        for(int i=0; i<10; i++) {
            s += "*";
            System.out.println(s);
        }
        System.out.println("Dear premium client this tree is for you");
    }
}
