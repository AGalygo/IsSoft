import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CargoTest {

    @Test
    void addWeight_valid() {
        Cargo car = cargo(50);
        car.addWeight(30);
        car.addWeight(20);

    }

    @Test
    void addWeight_invalid() {
        Cargo car = cargo(50);
        car.addWeight(30);
        assertThrows(IllegalArgumentException.class, () -> {
            car.addWeight(30);
        });

    }

    public static Cargo cargo(Integer capacity) {
        return Cargo.of(capacity);
    }
}