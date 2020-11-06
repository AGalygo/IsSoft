import com.google.common.base.Preconditions;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Driver extends Person {
    @Getter
    private Integer age;
    public static final Integer MIN_AGE = 18;

    private static final Logger logger = LoggerFactory.getLogger(
            Driver.class);

    private Driver(String name, String surname, Integer age) {
        super(name, surname);
        this.age = age;
        logger.info("new Driver has been created");
    }


    public static Driver of(String name, String surname, Integer age) {
        Preconditions.checkArgument(age >= MIN_AGE, "Driver is too young");
        return new Driver(name, surname, age);
    }

    public String detDriver() {
        return toString();
    }

    @Override
    public String toString() {
        return super.getName() + " " + super.getSurname() + " " + age + " years";
    }
}
