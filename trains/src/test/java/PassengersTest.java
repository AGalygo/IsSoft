import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class PassengersTest {

    @Test
    void addPassenger_valid() {
        Passengers pass = pass(5);
        List<Person> validPassengers = somePassengers(5);
        validPassengers.forEach(pass::addPassenger);

    }

    @Test
    void addPassenger_invalid() {
        Passengers pass = pass(5);
        List<Person> invalidPassengers = somePassengers(6);

        assertThrows(IllegalArgumentException.class, () -> {
            for (Person passenger : invalidPassengers) {
                pass.addPassenger(passenger);
            }
        });

    }

    public static Passengers pass(Integer amountOfPassengers) {
        return Passengers.of(amountOfPassengers);
    }

    public static List<Person> somePassengers(int size) {
        return IntStream.range(0, size).
                mapToObj(i -> Person.of("name", "surname")).
                collect(Collectors.toList());
    }
}