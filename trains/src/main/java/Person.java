import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class Person {
    private String name;
    private String surname;

    private static final Logger logger = LoggerFactory.getLogger(
            Person.class);

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        logger.info("new person has been created");
    }

    public static Person of(String name, String surname) {
        return new Person(name, surname);
    }

    public String getPerson() {
        return toString();
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
