package pl.wsb.hotel.domain.hotel.room;

import pl.wsb.hotel.domain.client.Client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RoomReservation {

    private final Client guest;
    private final Room room;
    private LocalDate reservationDate;
    private boolean isConfirmed;

    public RoomReservation(Client guest, Room room, LocalDate reservationDate) {
        this.guest = guest;
        this.room = room;
        this.reservationDate = reservationDate;
        this.isConfirmed = false;
    }

    public Client getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void confirmReservation() throws Exception {
        if(this.isConfirmed) {
            throw new Exception("Reservation is already confirmed");
        }

        this.isConfirmed = true;
    }

    public String getReservationInformation() {
        return
            "Details of reservation : \n"
            + "Reserved room ID: " + this.getRoom().getId() + "\n"
            + "Guest name: " + this.getGuest().getFullName() + "\n"
            + "Reservation date: " + this.getReservationDate().format(DateTimeFormatter.ISO_DATE) + "\n"
            + "Is reservation confirmed: " + (this.isConfirmed() ? "Yes" : "No") + "\n"
        ;
    }
}
