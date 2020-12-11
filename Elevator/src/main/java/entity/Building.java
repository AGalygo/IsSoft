package entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@Slf4j
public class Building {
    private String address;
    private Integer numberOfFloors;
    private Integer floorHeight;
    private List<Elevator> elevators;

    public Building(String address, Integer numberOfFloors, Integer floorHeight) {
        this.address = address;
        this.numberOfFloors = numberOfFloors;
        this.floorHeight = floorHeight;
        elevators = new ArrayList<>();
        log.info("Created new building: address " + address + " numberOfFloors " + numberOfFloors + " floor height " + floorHeight);
    }

    public static Building of(String address, Integer numberOfFloors, Integer floorHeight) {
        checkNotNull(numberOfFloors);
        return new Building(address, numberOfFloors, floorHeight);
    }

    public void addElevator(Elevator elevator) {
        checkNotNull(elevator);
        checkArgument(elevator.getMAX_FLOOR() <= numberOfFloors, "too high max floor in elevator");
        checkArgument(elevator.getFloorHeight() == floorHeight, "wrong floor height");
        elevators.add(elevator);
    }
}
