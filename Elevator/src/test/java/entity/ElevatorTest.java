package entity;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {
    public static final int ELEVATOR_CAPACITY = 220;
    public static final int BUILDING_NUMBER_OF_FLOORS = 15;
    public static final int BUILDING_FLOOR_HEIGHT = 3;

    @Test
    void addHuman_valid() {
        Elevator elevator = Elevator.of(ELEVATOR_CAPACITY, 3, 1, BUILDING_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        Human Alex = Human.of("Alex", 80, 3, 10);
        assertEquals(elevator.addHuman(Alex), true);

    }

    @Test
    void addHuman_invalid() {
        Elevator elevator = Elevator.of(ELEVATOR_CAPACITY, 3, 1, BUILDING_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        Human Alex = Human.of("Alex", 80, 3, 10);
        Human Olga = Human.of("Olga", 70, 5, 15);
        Human Anton = Human.of("Anton", 90, 8, 1);
        elevator.addHuman(Alex);
        elevator.addHuman(Olga);
        //elevator is full
        assertEquals(elevator.addHuman(Anton), false);
    }

    @Test
    void removeHuman_valid() {
        Elevator elevator = Elevator.of(ELEVATOR_CAPACITY, 3, 1, BUILDING_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        Human Alex = Human.of("Alex", 80, 3, 10);
        elevator.addHuman(Alex);
        elevator.removeHuman(Alex);
    }

    @Test
    void removeHuman_invalid() {
        Elevator elevator = Elevator.of(ELEVATOR_CAPACITY, 3, 1, BUILDING_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        Human Alex = Human.of("Alex", 80, 3, 10);
        Human Olga = Human.of("Olga", 70, 5, 15);
        elevator.addHuman(Alex);
        assertThrows(IllegalArgumentException.class, () -> {
            elevator.removeHuman(Olga);
        });
    }

    @Test
    void takeHuman_valid() {
        //Elevator direction - UP
        Elevator elevator = Elevator.of(ELEVATOR_CAPACITY, 3, 1, BUILDING_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        //Human direction - UP
        Human Alex = Human.of("Alex", 82, 3, 10);
        assertEquals(elevator.takeHuman(Alex), true);
    }

    @Test
    void takeHuman_invalid() {
        //Elevator direction - UP
        Elevator elevator = Elevator.of(ELEVATOR_CAPACITY, 3, 1, BUILDING_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        //Human direction - DOWN
        Human Alex = Human.of("Alex", 82, 10, 3);
        assertEquals(elevator.takeHuman(Alex), false);
    }

    @Test
    void moveUp_invalid() {
        Elevator elevator = Elevator.of(ELEVATOR_CAPACITY, 3, 1, BUILDING_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        assertThrows(IllegalArgumentException.class, () -> {
            IntStream.range(1, BUILDING_NUMBER_OF_FLOORS + 1).forEach(i -> elevator.moveUp());
        });
    }


    @Test
    void moveDown_invalid() {
        Elevator elevator = Elevator.of(ELEVATOR_CAPACITY, 3, 1, BUILDING_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        assertThrows(IllegalArgumentException.class, () -> {
            elevator.moveDown();
        });
    }


}