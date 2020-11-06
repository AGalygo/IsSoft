import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Getter
public class Train {
    private String number;
    private Carriage firstCarriage;
    @Setter
    private String departurePoint;
    @Setter
    private String arrivalPoint;
    private Integer carriageAmount;

    private static final Logger logger = LoggerFactory.getLogger(
            Train.class);

    private Train(String departurePoint, String arrivalPoint) {
        logger.info("New train has been created");
        this.number = UUID.randomUUID().toString();
        this.departurePoint = departurePoint;
        this.arrivalPoint = arrivalPoint;
        carriageAmount = 0;
    }

    public static Train of(String departurePoint, String arrivalPoint) {
        return new Train(departurePoint, arrivalPoint);
    }

    public void addCarriage(Carriage carriage) {
        Preconditions.checkNotNull(carriage);
        if (firstCarriage == null) {
            this.firstCarriage = carriage;
        } else {
            Carriage car = firstCarriage;
            while (car.getNext() != null) {
                car = car.getNext();
            }
            car.setNext(carriage);
        }
        carriageAmount++;
    }

    @Override
    public String toString() {
        String output = "Train:\nnumber: " + getNumber() + "\ndeparture point: " + departurePoint +
                "\narrival point: " + arrivalPoint + "\nCarriages: \n";
        Carriage car = firstCarriage;
        if (car != null) {
            output = output + car.toString() + '\n';
        }
        while (car.getNext() != null) {
            car = car.getNext();
            output = output + car.toString() + '\n';

        }
        return output;
    }

}
