package pl.wsb.hotel.domain.hotel.room;

public class Room {
    private final String id;

    public double getArea() {
        return area;
    }

    private final double area;
    private final int floor;
    private final boolean hasKingSizeBed;
    private boolean hasFridge;
    private boolean hasBalcony;
    private boolean hasCoffeeMachine;
    private String description;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room(String id, double area, int floor, boolean hasKingSizeBed, String description) {
        this.id = id;
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
        this.hasFridge = true;
        this.hasBalcony = false;
        this.hasCoffeeMachine = false;
        this.description= description;
    }

    public Room(String id, double area, int floor, boolean hasKingSizeBed, boolean hasBalcony) {
        this.id = id;
        this.area = area;
        this.floor = floor;
        this.hasKingSizeBed = hasKingSizeBed;
        this.hasFridge = true;
        this.hasBalcony = hasBalcony;
        this.hasCoffeeMachine = false;
    }

    public String getId() {
        return id;
    }

    public void setHasFridge(boolean hasFridge) {
        this.hasFridge = hasFridge;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public void setHasCoffeeMachine(boolean hasCoffeeMachine) {
        this.hasCoffeeMachine = hasCoffeeMachine;
    }

    public String showRoomData() {
        return
            "Data of room : " + this.getId() + "\n"
            + "Area of room: " + this.area + "\n"
            + "Room floor : " + this.floor + "\n"
            + "Has room king bed: " + (this.hasKingSizeBed ? "Yes" : "No") + "\n"
            + "Has room fridge: " + (this.hasFridge ? "Yes" : "No") + "\n"
            + "Has room balcony: " + (this.hasBalcony ? "Yes" : "No") + "\n"
            + "Has room coffee machine: " + (this.hasCoffeeMachine ? "Yes" : "No") + "\n"
        ;
    }
}
