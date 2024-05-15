package pl.wsb.hotel.domain.hotel;

import pl.wsb.hotel.domain.client.Client;
import pl.wsb.hotel.domain.client.exception.ClientNotFoundException;
import pl.wsb.hotel.domain.hotel.room.Room;
import pl.wsb.hotel.domain.hotel.room.RoomReservation;
import pl.wsb.hotel.domain.hotel.room.exception.ReservationNotFoundException;
import pl.wsb.hotel.domain.hotel.room.exception.RoomNotFoundException;
import pl.wsb.hotel.domain.hotel.room.exception.RoomReservedException;
import pl.wsb.hotel.domain.hotel.service.AbstractService;
import pl.wsb.hotel.domain.hotel.HotelCapability;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Hotel implements HotelCapability{
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
        if(this.specialServices == null) {
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
        try {
            return this.clients.stream()
                    .filter(client -> {
                        try {
                            return client.getAge()<18;
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .findFirst()
                    .orElse(null).getAge();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String addRoom(double area, int floor, boolean hasKingSizeBed, String description) {
        return null;
    }

    @Override
    public double getRoomArea(String roomId) {
        return 0;
    }

    @Override
    public int getNumberOfRoomsWithKingSizeBed(int floor) {
        return 0;
    }

    @Override
    public String addNewReservation(String clientId, String roomId, LocalDate date) throws ClientNotFoundException, RoomNotFoundException, RoomReservedException {
        return null;
    }

    @Override
    public String confirmReservation(String reservationId) throws ReservationNotFoundException {
        return null;
    }

    @Override
    public boolean isRoomReserved(String roomId, LocalDate date) throws RoomNotFoundException {
        return false;
    }

    @Override
    public int getNumberOfUnconfirmedReservation(LocalDate date) {
        return 0;
    }

    @Override
    public Collection<String> getRoomIdsReservedByClient(String clientId) throws ClientNotFoundException {
        return null;
    }

    public String addClient(String firstName, String lastName, LocalDate birthDate) {
        if(this.clients == null) {
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
        if(this.reservations == null) {
            this.reservations = new ArrayList<>();
        }

        this.reservations.add(reservation);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        if(this.rooms == null) {
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
