package pl.wsb.hotel;

import pl.wsb.hotel.domain.client.Client;
import pl.wsb.hotel.domain.hotel.Hotel;
import pl.wsb.hotel.domain.hotel.room.Room;
import pl.wsb.hotel.domain.hotel.room.RoomReservation;
import pl.wsb.hotel.domain.client.PremiumClient;
import pl.wsb.hotel.domain.client.PremiumClientType;
import pl.wsb.hotel.domain.hotel.service.LuggageService;
import pl.wsb.hotel.domain.hotel.service.TimeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Hotel hotel = new Hotel("⭐⭐⭐⭐⭐ Hotel Rodzynek ^^ ⭐⭐⭐⭐⭐");

        Client firstClient = new Client("Client no. 1", "Dawid", "Tomas", LocalDate.of(1998, 6, 1), "Wrocław");
        Client secondClient = new PremiumClient("Client no. 2", "Albert", "Einstein", LocalDate.of(1879, 3, 14));
        PremiumClient thirdClient = new PremiumClient("Client no. 3", "Mona", "Lisa", LocalDate.of(1503, 1, 1));

        Room firstRoom = new Room("Room no. 1", 10.00, 2, false, false);
        Room secondRoom = new Room("Room no. 2", 35.00, 4, false, true);
        Room thirdRoom = new Room("Room no. 3", 66.00, 7, true,"ale bydle");

        RoomReservation firstReservation = new RoomReservation(firstClient, secondRoom, LocalDate.of(2024, 3, 20));
        RoomReservation secondReservation = new RoomReservation(thirdClient, firstRoom, LocalDate.of(2024, 4, 10));
        RoomReservation thirdReservation = new RoomReservation(secondClient, thirdRoom, LocalDate.of(2024, 5, 5));

        firstClient.setCity("Świdnica");
        firstClient.setStreet("ul. Testowa 2");
        firstClient.setZipCode("58-100");
        firstClient.setPhoneNumber(234234234);

        secondClient.setPhoneNumber(123123123);

        thirdClient.setPremiumAccountType(PremiumClientType.PREMIUM_PLUS);

        firstReservation.confirmReservation();
        secondReservation.confirmReservation();
        thirdReservation.setReservationDate(LocalDate.of(2024, 7, 11));

        // LeftToDo Implement usage of Hotel class + other new things

        List<Client> clients = new ArrayList<>();
        List<RoomReservation> roomReservations = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();

        clients.add(firstClient);
        clients.add(secondClient);
        clients.add(thirdClient);

        roomReservations.add(firstReservation);
        roomReservations.add(secondReservation);
        roomReservations.add(thirdReservation);

        rooms.add(firstRoom);
        rooms.add(secondRoom);
        rooms.add(thirdRoom);

        hotel.setClients(clients);
        hotel.setReservations(roomReservations);
        hotel.setRooms(rooms);

        TimeService timeService = new TimeService();
        LuggageService luggageService = new LuggageService();


        System.out.println("\n" + hotel.getName() + "\n");
        LocalDateTime now = LocalDateTime.now();
        // implementation of a simple Client list
        for (int i = 0; i < clients.size(); i++) {
            System.out.println("==================================");
            System.out.println("Information about client no. :" + i+1);
            System.out.print(hotel.getClients().get(i).getAllSummaries());

            if (i==1)

                luggageService.orderService();
                luggageService.checkServiceAvailability(now);
            if (i==2)
                timeService.orderService();
        }

        System.out.println("==================================");
        System.out.println("Information's about room no. 2 & no 3.:");
        System.out.println(hotel.getRooms().get(1).showRoomData()); //the second
        System.out.println(hotel.getRooms().get(2).showRoomData()); //the third

        System.out.println("==================================");
        System.out.println("Information's about reservations no. 1 & no 3.:\n");
        System.out.println(hotel.getReservations().get(0).getReservationInformation());
        System.out.println(hotel.getReservations().get(2).getReservationInformation());
        //wywołanie medoty abstrakcyjnej nadpisanej w klasie dziedziczonej - pkt z gwiazda
        timeService.getfullinfoinpl();

    }
}
