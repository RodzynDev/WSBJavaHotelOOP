package pl.wsb.hotel.domain.hotel;

import pl.wsb.hotel.domain.client.Client;
import pl.wsb.hotel.domain.client.exception.ClientNotFoundException;
import pl.wsb.hotel.domain.hotel.room.Room;
import pl.wsb.hotel.domain.hotel.room.RoomReservation;
import pl.wsb.hotel.domain.hotel.room.exception.ReservationNotFoundException;
import pl.wsb.hotel.domain.hotel.room.exception.RoomNotFoundException;
import pl.wsb.hotel.domain.hotel.room.exception.RoomReservedException;
import pl.wsb.hotel.domain.hotel.service.AbstractService;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Hotel implements HotelCapability {
    private final String name;
    private List<AbstractService> specialServices;
    private List<Client> clients;
    private List<RoomReservation> reservations;
    private List<Room> rooms;

    public Hotel(String name) {
        this.name = name;

        this.specialServices = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<AbstractService> getSpecialServices() {
        return specialServices;
    }

    public void addSpecialService(AbstractService service) {
        if (this.specialServices == null) {
            this.specialServices = new ArrayList<>();
        }

        this.specialServices.add(service);
    }

    public List<Client> getClients() {
        return clients;
    }

    public String getClientFullName(String clientId) {
        return this.clients.stream()
                .filter(client -> client.getId().equals(clientId))
                .map(client -> String.format("%s", client.getFullName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int getNumberOfUnderageClients() {
        LocalDate today = LocalDate.now();
        return (int) this.clients.stream()
                .filter(client -> Period.between(client.getBirthDate(), today).getYears() < 18)
                .count();
    }

    @Override
    public String addRoom(double area, int floor, boolean hasKingSizeBed, String description) {
        if (this.rooms == null) {
            this.rooms = new ArrayList<>();
        }

        String generatedRoomId = String.valueOf(this.rooms.size() + 1);
        Room room = new Room(generatedRoomId, area, floor, hasKingSizeBed, description);
        this.rooms.add(room);

        return room.getId();
    }

    @Override
    public double getRoomArea(String roomId) {
        return this.rooms.stream()
                .filter(room -> room.getId().equals(roomId))
                .map(Room::getArea)
                .findFirst()
                .orElse(0.0);
    }

    @Override
    public int getNumberOfRoomsWithKingSizeBed(int floor) {
        return (int) this.rooms.stream()
                .filter(room -> room.getFloor() == floor && room.isHasKingSizeBed())
                .count();
    }

    @Override
    public String addNewReservation(String clientId, String roomId, LocalDate date) throws ClientNotFoundException, RoomNotFoundException, RoomReservedException {
        if (this.clients.stream().noneMatch(client -> client.getId().equals(clientId))) {
            throw new ClientNotFoundException("Client not found");
        }

        if (this.rooms.stream().noneMatch(room -> room.getId().equals(roomId))) {
            throw new RoomNotFoundException("Room not found");
        }

        if (this.reservations.stream().anyMatch(reservation -> reservation.getRoom().getId().equals(roomId) && reservation.getReservationDate().equals(date))) {
            throw new RoomReservedException("Room is already reserved", date);
        }

        Room room = this.rooms.stream()
                .filter(r -> r.getId().equals(roomId))
                .findFirst()
                .orElse(null);

        Client client = this.clients.stream()
                .filter(c -> c.getId().equals(clientId))
                .findFirst()
                .orElse(null);

        RoomReservation reservation = new RoomReservation(String.valueOf(this.reservations.size() + 1), client, room, date);
        this.reservations.add(reservation);

        return reservation.getId();
    }

    @Override
    public String confirmReservation(String reservationId) throws ReservationNotFoundException {
        RoomReservation reservation = this.reservations.stream()
                .filter(r -> r.getId().equals(reservationId))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(reservation)) {
            throw new ReservationNotFoundException("Reservation not found");
        }

        reservation.confirmReservation();

        return reservation.getId();
    }

    @Override
    public boolean isRoomReserved(String roomId, LocalDate date) throws RoomNotFoundException {
        if (this.rooms.stream().noneMatch(room -> room.getId().equals(roomId))) {
            throw new RoomNotFoundException("Room not found");
        }

        return this.reservations.stream()
                .anyMatch(reservation -> reservation.getRoom().getId().equals(roomId) && reservation.getReservationDate().equals(date));
    }

    @Override
    public int getNumberOfUnconfirmedReservation(LocalDate date) {
        return (int) this.reservations.stream()
                .filter(reservation -> reservation.getReservationDate().equals(date) && !reservation.isConfirmed())
                .count();
    }

    @Override
    public Collection<String> getRoomIdsReservedByClient(String clientId) throws ClientNotFoundException {
        if (this.clients.stream().noneMatch(client -> client.getId().equals(clientId))) {
            throw new ClientNotFoundException("Client not found");
        }

        return this.reservations.stream()
                .filter(reservation -> reservation.getGuest().getId().equals(clientId))
                .map(reservation -> reservation.getRoom().getId())
                .toList();
    }

    public String addClient(String firstName, String lastName, LocalDate birthDate) {
        if (this.clients == null) {
            this.clients = new ArrayList<>();
        }

        String generatedClientId = String.valueOf(this.clients.size() + 1);
        Client client = new Client(generatedClientId, firstName, lastName, birthDate);

        this.clients.add(client);
        return client.getId();
    }


    public List<RoomReservation> getReservations() {
        return reservations;
    }

    public void addReservation(RoomReservation reservation) {
        if (this.reservations == null) {
            this.reservations = new ArrayList<>();
        }

        this.reservations.add(reservation);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        if (this.rooms == null) {
            this.rooms = new ArrayList<>();
        }

        this.rooms.add(room);
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setReservations(List<RoomReservation> reservations) {
        this.reservations = reservations;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
