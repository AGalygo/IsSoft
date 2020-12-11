import entity.Human;
import calls.CallCreator;
import calls.Calls;
import entity.Building;
import entity.Elevator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Calls calls = Calls.getInstance();
        calls.addCall(Human.of("Alex", 80, 8, 11));
        calls.addCall(Human.of("Alena", 80, 11, 3));
        calls.addCall(Human.of("James", 80, 10, 1));
        calls.addCall(Human.of("llll", 80, 10, 1));
        calls.addCall(Human.of("4444", 80, 4, 8));
        calls.addCall(Human.of("5555", 80, 9, 6));
        calls.addCall(Human.of("6666", 80, 2, 6));


        Scanner scanner = new Scanner(System.in);

        System.out.println("Building parameters: \n 1) input address of the building: ");
        String address = scanner.nextLine();

        System.out.println("2) input number of floors: ");
        int numOfFloors = scanner.nextInt();

        System.out.println("input floor height (m): ");
        Integer floorHeight = scanner.nextInt();

        Building building = Building.of(address, numOfFloors, floorHeight);

        System.out.println("Input number of Elevators: ");
        Integer number = scanner.nextInt();

        System.out.println("Elevator parameters: \n");
        for (int i = 0; i < number; i++) {
            System.out.println(i + 1 + "elevator parameters: \n input capacity: >200 ");
            Integer capacity = scanner.nextInt();
            while (capacity < 200) {
                System.out.println("capacity must be > 200. Input one more time, please");
                capacity = scanner.nextInt();
            }
            System.out.println("speed: ");
            Integer speed = scanner.nextInt();
            System.out.println("time for opening/ closing doors: ");
            Integer time = scanner.nextInt();
            building.addElevator(Elevator.of(capacity, speed, time, numOfFloors, floorHeight));
        }

        CallCreator callCreator = new CallCreator(numOfFloors);
        Thread callersThread = new Thread(callCreator);
        callersThread.setName("CallCreator");
        callersThread.start();

        for (int i = 0; i < number; i++) {  //переписать оба фора
            Thread elevatorsThread = new Thread(building.getElevators().get(i));
            elevatorsThread.setName("Elevator-"+(i+1));
            elevatorsThread.start();
        }

    }
}
