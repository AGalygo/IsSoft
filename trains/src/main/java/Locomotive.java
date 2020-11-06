
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Getter
@Setter
public class Locomotive extends Carriage {

    private Driver driver;

    private static final Logger logger = LoggerFactory.getLogger(
            Locomotive.class);

    private Locomotive(String number) {

        super(number);
        logger.info("new locomotive has been created");
    }


    public static Locomotive ofNumber() {
        return new Locomotive(UUID.randomUUID().toString());
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return this.driver;
    }

    @Override
    public String toString() {
        return "Locomotive: \nnumber: " + super.getNumber()
                + "\ndriver: " + driver.toString();
    }
}
