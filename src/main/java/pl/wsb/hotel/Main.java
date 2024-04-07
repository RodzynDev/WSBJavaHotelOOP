package pl.wsb.hotel;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        Client firstClient = new Client("Client no. 1", "Dawid", "Tomas", LocalDate.of(1998, 6, 1));
        Client secondClient = new Client("Client no. 2", "Albert", "Einstein", LocalDate.of(1879, 3, 14), "Wrocław");
        Client thirdtClient = new Client("Client no. 3", "Mona", "Lisa", LocalDate.of(1503, 1, 1));

        Room firstRoom = new Room("Room no. 1", 10.00, 2, false, false);
        Room secondRoom = new Room("Room no. 2", 35.00, 4, false, true);
        Room thirdRoom = new Room("Room no. 3", 66.00, 7, true);

        RoomReservation firstReservation = new RoomReservation(firstClient, secondRoom, LocalDate.of(2024, 3, 20));
        RoomReservation secondReservation = new RoomReservation(thirdtClient, firstRoom, LocalDate.of(2024, 4, 10));
        RoomReservation thirdReservation = new RoomReservation(secondClient, thirdRoom, LocalDate.of(2024, 5, 5));

        firstClient.setCity("Świdnica");
        firstClient.setStreet("ul. Testowa 2");
        firstClient.setZipCode("58-100");
        firstClient.setPhoneNumber(234234234);

        secondClient.setPhoneNumber(123123123);

        firstReservation.confirmReservation();
        secondReservation.confirmReservation();
        thirdReservation.setReservationDate(LocalDate.of(2024, 7, 11));

        System.out.println("==================================");
        System.out.println("Information's about client no. 1:");
        System.out.println(firstClient.getAllSummaries());

        System.out.println("Information's about client no. 2:");
        System.out.println(secondClient.getAllSummaries());

        System.out.println("==================================");
        System.out.println("Information's about room no. 2 & no 3.:");
        System.out.println(secondRoom.showRoomData());
        System.out.println(thirdRoom.showRoomData());

        System.out.println("==================================");
        System.out.println("Information's about reservations no. 1 & no 3.:\n");
        System.out.println(firstReservation.getReservationInformation());
        System.out.println(thirdReservation.getReservationInformation());
    }
}
