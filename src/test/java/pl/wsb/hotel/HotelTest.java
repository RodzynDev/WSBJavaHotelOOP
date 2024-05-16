package pl.wsb.hotel;

import org.junit.jupiter.api.Test;
import pl.wsb.hotel.domain.client.Client;
import pl.wsb.hotel.domain.client.exception.ClientNotFoundException;
import pl.wsb.hotel.domain.hotel.Hotel;
import pl.wsb.hotel.domain.hotel.room.exception.RoomNotFoundException;
import pl.wsb.hotel.domain.hotel.service.LuggageService;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class HotelTest {
    private final Hotel hotel = new Hotel("Test Hotel");

    @Test
    void testAddClient() {
        hotel.addClient("Dawid", "Tomas", LocalDate.of(1998, 6, 1));

        assertEquals(1, hotel.getClients().size());
    }

    @Test
    void testClientFullName() {
        Client client = new Client("1", "Dawid", "Tomas", LocalDate.of(1998, 6, 1));
        hotel.getClients().add(client);

        assertEquals("Dawid Tomas", hotel.getClientFullName("1"));
    }

    @Test
    void checkNumberOfUnderageClients() {
        hotel.addClient("Dawid", "Tomas", LocalDate.of(1998, 6, 1));
        hotel.addClient("Albert", "Einstein", LocalDate.of(1879, 3, 14));
        hotel.addClient("Mona", "Lisa", LocalDate.of(1503, 1, 1));

        assertEquals(0, hotel.getNumberOfUnderageClients());
    }

    @Test
    void testAddRoom() {
        hotel.addRoom(10.00, 2, false, "Room no. 1");

        assertEquals(1, hotel.getRooms().size());
    }

    @Test
    void testGetRoomArea() {
        String createdRoom = hotel.addRoom(10.00, 2, false, "Room no. 1");

        System.out.println(hotel.getRoomArea(createdRoom));
        System.out.println(createdRoom);

        assertEquals(10.00, hotel.getRoomArea(createdRoom));
    }

    @Test
    void testNumberOfRoomsWithKingSizeBed() {
        hotel.addRoom(10.00, 2, false, "Room no. 1");
        hotel.addRoom(35.00, 4, false, "Room no. 2");
        hotel.addRoom(66.00, 7, true, "Room no. 3");

        assertEquals(1, hotel.getNumberOfRoomsWithKingSizeBed(7));
    }

    @Test
    void testAddNewReservation() throws ClientNotFoundException, RoomNotFoundException {
        hotel.addClient("Dawid", "Tomas", LocalDate.of(1998, 6, 1));
        hotel.addRoom(10.00, 2, false, "Room no. 1");

        String reservationId = hotel.addNewReservation("1", "1", LocalDate.of(2024, 3, 20));

        assertNotNull(reservationId);
    }

    @Test
    void testConfirmReservation() throws ClientNotFoundException, RoomNotFoundException {
        hotel.addClient("Dawid", "Tomas", LocalDate.of(1998, 6, 1));
        hotel.addRoom(10.00, 2, false, "Room no. 1");

        String reservationId = hotel.addNewReservation("1", "1", LocalDate.of(2024, 3, 20));

        assertDoesNotThrow(() -> hotel.confirmReservation(reservationId));
    }

    @Test
    void testIsRoomReserved() throws RoomNotFoundException, ClientNotFoundException {
        hotel.addClient("Dawid", "Tomas", LocalDate.of(1998, 6, 1));
        hotel.addRoom(10.00, 2, false, "Room no. 1");

        hotel.addNewReservation("1", "1", LocalDate.of(2024, 3, 20));

        assertTrue(hotel.isRoomReserved("1", LocalDate.of(2024, 3, 20)));
    }

    @Test
    void testGetNumberOfUnconfirmedReservation() throws ClientNotFoundException, RoomNotFoundException {
        hotel.addClient("Dawid", "Tomas", LocalDate.of(1998, 6, 1));
        hotel.addRoom(10.00, 2, false, "Room no. 1");

        hotel.addNewReservation("1", "1", LocalDate.of(2024, 3, 20));

        assertEquals(1, hotel.getNumberOfUnconfirmedReservation(LocalDate.of(2024, 3, 20)));
    }

    @Test
    void testGetRoomIdsReservedByClient() throws ClientNotFoundException, RoomNotFoundException {
        hotel.addClient("Dawid", "Tomas", LocalDate.of(1998, 6, 1));
        hotel.addRoom(10.00, 2, false, "Room no. 1");

        hotel.addNewReservation("1", "1", LocalDate.of(2024, 3, 20));

        assertDoesNotThrow(() -> hotel.getRoomIdsReservedByClient("1"));
    }

    @Test
    void testAddSpecialService() {
        LuggageService luggageService = new LuggageService();
        hotel.addSpecialService(luggageService);

        assertTrue(hotel.getSpecialServices().contains(luggageService));
    }
}
