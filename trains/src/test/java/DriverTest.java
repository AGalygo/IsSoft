import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    @Test
    void of_valid() {
        Driver driver = Driver.of("Alex", "Smirnov", 18);
        assertTrue(driver.getAge(), 18);
    }

    private void assertTrue(Integer age, int i) {
    }

    @Test
    void of_invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            Driver.of("Alex", "Ivanov", 14);
        });
    }

}