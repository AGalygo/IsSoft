package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {
    public static final int VALID_HUMAN_WEIGHT = 400;
    public static final int INVALID_HUMAN_WEIGHT = VALID_HUMAN_WEIGHT + 1;

    @Test
    void of_valid() {
        Human human = Human.of("Alesia", VALID_HUMAN_WEIGHT, 6, 10);
    }

    @Test
    void of_invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            Human human = Human.of("Alesia", INVALID_HUMAN_WEIGHT, 6, 10);
        });
    }
}