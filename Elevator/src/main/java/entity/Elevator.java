package entity;

import Enum.Direction;
import calls.Calls;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static Enum.Direction.DOWN;
import static Enum.Direction.UP;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@Slf4j
public class Elevator implements Runnable {
    private Integer capacity;
    private final Integer speed;
    private final Integer timeOpenCloseDoors;
    private Integer currentFloor;
    private final Integer floorHeight;
    private List<Human> passengers;
    private final Integer MAX_FLOOR;
    private Direction direction;
    private Human call;
    private boolean stopped;

    private Integer nextStopFloor;

    Calls tasks = Calls.getInstance();

    @Override
    public String toString() {
        return "Elevator{" +
                "capacity=" + capacity +
                ", speed=" + speed +
                ", timeOpenCloseDoors=" + timeOpenCloseDoors +
                '}';
    }

    public Elevator(Integer capacity, Integer speed, Integer timeOpenCloseDoors, Integer maxFloor, Integer floorHeight) {
        this.capacity = capacity;
        this.speed = speed;
        this.timeOpenCloseDoors = timeOpenCloseDoors;
        this.currentFloor = 1;
        this.floorHeight = floorHeight;
        passengers = new ArrayList<>();
        this.MAX_FLOOR = maxFloor;
        this.direction = UP;
        this.call = null;
        stopped = false;
        nextStopFloor = 0;
        log.info("Created new elevator " + toString());
    }

    public static Elevator of(Integer capacity, Integer speed, Integer timeOpenCloseDoors, Integer maxFloor, Integer floorHeight) {
        return new Elevator(capacity, speed, timeOpenCloseDoors, maxFloor, floorHeight);
    }

    @Override
    public void run() {
        init();
    }

    @SneakyThrows
    private void init() {
        do {
            call = tasks.removeCall();
            if (call != null) {
                log.info("Elevator received a call (" + call.getName() + " from: " + call.getBoardingFloor() + " to: " + call.getDestinationFloor() + ")");
                move(call);
            } else {
                log.info("waiting for passengers");
                TimeUnit.SECONDS.sleep(2);
            }

        } while (!stopped);
    }

    @SneakyThrows
    public void move(Human human) {

        nextStopFloor = human.getBoardingFloor();

        if (currentFloor - human.getBoardingFloor() < 0) {
            this.direction = UP;
        } else this.direction = DOWN;

        if (currentFloor == human.getBoardingFloor()) {
            nextStopFloor = human.getDestinationFloor();
            this.direction = human.getDirection();
            takeHuman(human);
        }

        while (passengers.size() != 0 || call != null) {
            checkFloor();
            while (currentFloor != nextStopFloor) {
                moveOnDirection(direction);
                checkFloor();
                if (call != null) {
                    if (currentFloor == human.getBoardingFloor()) {
                        takeHuman(human);
                    }
                }
            }
            changeDirection();
            if (call != null) {
                if (currentFloor == human.getBoardingFloor()) {
                    takeHuman(human);
                }
            }
            if (call != null) {
                nextStopFloor = human.getBoardingFloor();
            }
        }
    }

    public boolean addHuman(Human human) {
        try {
            checkNotNull(human);
            checkArgument(human.getBoardingFloor() < MAX_FLOOR && human.getDestinationFloor() <= MAX_FLOOR,
                    "Too high destination or boarding floor");
            checkArgument(human.getWeight() < capacity, "Elevator is full or passenger is too heavy");
        } catch (IllegalArgumentException e) {
            return false;
        }
        passengers.add(human);
        openAndCloseDoors();
        this.capacity -= human.getWeight();
        log.info("passenger " + human.getName() + " is on board (from: " + human.getBoardingFloor() + " to: " + human.getDestinationFloor() + " )");
        log.info("Now " + this.passengers.size() + " passengers are in elevator");
        return true;
    }

    public void removeHuman(Human human) {
        checkArgument(passengers.contains(human));
        this.capacity += human.getWeight();
        passengers.remove(human);
        openAndCloseDoors();
        log.info("Passenger " + human.getName() + " is on his floor (from: " + human.getBoardingFloor() + " to: " + human.getDestinationFloor() + " )");
        log.info("Now " + this.passengers.size() + " passengers are in elevator");
    }

    public boolean takeHuman(Human human) {
        if (human.getDirection() == direction) {
            if (!addHuman(human)) {
                tasks.addCall(human);
                call = null;
            } else {
                call = null;
                if (human.getDestinationFloor() > nextStopFloor) {
                    if (direction.equals(UP)) {
                        nextStopFloor = human.getDestinationFloor();
                    }
                } else {
                    if (direction.equals(DOWN)) {
                        nextStopFloor = human.getDestinationFloor();
                    }
                }
            }
            return true;
        } else return false;
    }

    public void changeDirection() {
        if (this.direction == UP) {
            direction = DOWN;
        } else {
            direction = UP;
        }
    }

    @SneakyThrows
    public void moveOnDirection(Direction direction) {
        if (this.direction == UP) {
            moveUp();
        } else {
            moveDown();
        }
    }

    @SneakyThrows
    public void moveUp() {
        this.currentFloor++;
        checkArgument(currentFloor <= MAX_FLOOR);
        TimeUnit.SECONDS.sleep(floorHeight / speed);
    }

    @SneakyThrows
    public void moveDown() {
        this.currentFloor--;
        checkArgument(currentFloor >= 1);
        TimeUnit.SECONDS.sleep(floorHeight / speed);
    }

    public void checkFloor() {
        log.info("current floor is " + currentFloor);
        checkCallsInElevator();
        checkCallsInFloor(direction);
    }

    public void checkCallsInFloor(Direction direction) {

        Human passenger = null;

        while ((passenger = tasks.findCalls(direction, currentFloor, capacity)) != null) {
            addHuman(passenger);
            if (passenger.getDestinationFloor() > nextStopFloor) {
                if (direction.equals(UP)) {
                    nextStopFloor = passenger.getDestinationFloor();
                }
            } else {
                if (direction.equals(DOWN)) {
                    nextStopFloor = passenger.getDestinationFloor();
                }
            }
        }
    }

    public void checkCallsInElevator() {
        List<Human> list = passengers.stream().filter(p -> p.getDestinationFloor() == currentFloor).collect(Collectors.toList());
        list.forEach(p -> removeHuman(p));
    }

    @SneakyThrows
    private void openAndCloseDoors() {
        TimeUnit.SECONDS.sleep(timeOpenCloseDoors);
        log.info("elevator opened and closed doors");
    }

    public void stop() {
        this.stopped = true;
    }

}
