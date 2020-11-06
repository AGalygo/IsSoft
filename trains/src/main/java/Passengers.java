import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Passengers extends Carriage {
    @Setter
    private Integer capacity;
    private List<Person> passengers;

    public static Integer MAX_CAPACITY = 100;

    private static final Logger logger = LoggerFactory.getLogger(
            Passengers.class);

    private Passengers(Integer capacity) {
        super(UUID.randomUUID().toString());
        this.capacity = capacity;
        this.passengers = new ArrayList<Person>();
        logger.info("new passengers has been created");

    }

    public static Passengers of(Integer capacity) {
        Preconditions.checkArgument(capacity > 0 || capacity <= MAX_CAPACITY, "too big capacity");
        return new Passengers(capacity);

    }

    @Override
    public void setNext(Carriage next) {
        Preconditions.checkNotNull(next);
        super.setNext(next);
    }

    public String addPassenger(Person person) {
        Preconditions.checkNotNull(person, "dont have passenger");
        Preconditions.checkArgument(passengers.size() <= capacity - 1, "this carriage is full");
        passengers.add(person);
        return "successfully added";
    }

    public List<Person> getPassengers() {
        return List.copyOf(passengers);
    }

    @Override
    public String toString() {
        return "Passengers: \nnumber: " + super.getNumber()
                + "\ncapacity: " + capacity + "\npassengers: " + getPassengers();
    }
}
