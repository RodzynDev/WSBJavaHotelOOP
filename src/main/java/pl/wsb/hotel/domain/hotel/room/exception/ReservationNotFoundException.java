package pl.wsb.hotel.domain.hotel.room.exception;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(String message) {
        super(message);
    }
}
