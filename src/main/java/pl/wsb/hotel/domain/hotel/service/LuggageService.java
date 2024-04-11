package pl.wsb.hotel.domain.hotel.service;

import java.time.LocalDateTime;

public class LuggageService extends AbstractService {
    protected LuggageService() {
        super( "Luggage service", true);
    }

    @Override
    void orderService() {

    }

    @Override
    void checkServiceAvailability(LocalDateTime orderTime) {

    }
}
