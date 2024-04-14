package pl.wsb.hotel.domain.hotel;

import pl.wsb.hotel.domain.client.Client;
import pl.wsb.hotel.domain.hotel.room.Room;
import pl.wsb.hotel.domain.hotel.room.RoomReservation;
import pl.wsb.hotel.domain.hotel.service.AbstractService;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
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

    public void addClient(Client client) {
        if(this.clients == null) {
            this.clients = new ArrayList<>();
        }

        this.clients.add(client);
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
