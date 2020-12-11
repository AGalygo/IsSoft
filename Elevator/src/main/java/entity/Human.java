package entity;

import Enum.Direction;
import com.google.common.base.Preconditions;
import lombok.Getter;

@Getter
public class Human {

    private String name;
    private int weight;
    private int boardingFloor;
    private int destinationFloor;
    private Direction direction;
    private static Integer MAX_WEIGHT = 400;

    public Human(String name, int weight, int boardingFloor, int destinationFloor) {
        this.name = name;
        this.weight = weight;
        this.boardingFloor = boardingFloor;
        this.destinationFloor = destinationFloor;
        if (destinationFloor >= boardingFloor) {
            direction = Direction.UP;
        } else direction = Direction.DOWN;
    }

    public static Human of(String name, int weight, int boardingFloor, int destinationFloor) {
        Preconditions.checkArgument(weight <= MAX_WEIGHT, "Too heavy passenger");
        return new Human(name, weight, boardingFloor, destinationFloor);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", boardingFloor=" + boardingFloor +
                ", destinationFloor=" + destinationFloor +
                ", direction=" + direction +
                '}';
    }
}
