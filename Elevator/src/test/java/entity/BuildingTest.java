package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BuildingTest {
    public static final int BUILDING_NUMBER_OF_FLOORS = 15;
    public static final int BUILDING_FLOOR_HEIGHT = 3;
    public static final int VALID_ELEVATOR_NUMBER_OF_FLOORS = BUILDING_NUMBER_OF_FLOORS;
    public static final int INVALID_ELEVATOR_NUMBER_OF_FLOORS = BUILDING_NUMBER_OF_FLOORS + 1;


    @Test
    void addElevator_valid() {
        Building building = Building.of("Minsk", BUILDING_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        Elevator elevator = Elevator.of(250, 3, 1, VALID_ELEVATOR_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        building.addElevator(elevator);
    }

    @Test
    void addElevator_invalid() {
        Building building = Building.of("Minsk", BUILDING_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        Elevator elevator = Elevator.of(250, 3, 1, INVALID_ELEVATOR_NUMBER_OF_FLOORS, BUILDING_FLOOR_HEIGHT);
        assertThrows(IllegalArgumentException.class, () -> {
            building.addElevator(elevator);
        });
    }
}