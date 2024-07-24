import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String type;
    private boolean isOccupied;
    private String guestName;

    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isOccupied = false;
        this.guestName = "";
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void checkIn(String guestName) {
        this.isOccupied = true;
        this.guestName = guestName;
    }

    public void checkOut() {
        this.isOccupied = false;
        this.guestName = "";
    }

    public String getGuestName() {
        return guestName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", type='" + type + '\'' +
                ", isOccupied=" + isOccupied +
                ", guestName='" + guestName + '\'' +
                '}';
    }
}

class Hotel {
    private ArrayList<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
    }

    public void addRoom(int roomNumber, String type) {
        rooms.add(new Room(roomNumber, type));
    }

    public Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public void checkIn(int roomNumber, String guestName) {
        Room room = findRoom(roomNumber);
        if (room != null && !room.isOccupied()) {
            room.checkIn(guestName);
            System.out.println("Checked in " + guestName + " to room " + roomNumber);
        } else {
            System.out.println("Room " + roomNumber + " is either occupied or does not exist.");
        }
    }

    public void checkOut(int roomNumber) {
        Room room = findRoom(roomNumber);
        if (room != null && room.isOccupied()) {
            room.checkOut();
            System.out.println("Checked out room " + roomNumber);
        } else {
            System.out.println("Room " + roomNumber + " is either not occupied or does not exist.");
        }
    }

    public void viewStatus() {
        for (Room room : rooms) {
            System.out.println(room);
        }
    }
}

public class HotelManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        hotel.addRoom(101, "Single");
        hotel.addRoom(102, "Double");
        hotel.addRoom(103, "Suite");
        

        while (true) {
            System.out.println("\nHotel Management System:");
            System.out.println("1. Check in");
            System.out.println("2. Check out");
            System.out.println("3. View status");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    hotel.checkIn(roomNumber, guestName);
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    roomNumber = scanner.nextInt();
                    hotel.checkOut(roomNumber);
                    break;
                case 3:
                    hotel.viewStatus();
                    break;
                case 4:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}