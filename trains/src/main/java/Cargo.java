import com.google.common.base.Preconditions;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Getter
public class Cargo extends Carriage {

    private Integer payloadCapacity;
    private Integer weight;

    public static Integer MAX_CAPACITY = 100000;

    private static final Logger logger = LoggerFactory.getLogger(
            Cargo.class);

    private Cargo(Integer payloadCapacity) {
        super(UUID.randomUUID().toString());
        this.payloadCapacity = payloadCapacity;
        this.weight = 0;
        logger.info("new cargo has been created");
    }

    public static Cargo of(Integer payloadCapacity) {
        Preconditions.checkArgument(payloadCapacity > 0 || payloadCapacity <= MAX_CAPACITY, "too big capacity");
        return new Cargo(payloadCapacity);
    }

    @Override
    public void setNext(Carriage next) {
        Preconditions.checkNotNull(next);
        super.setNext(next);
    }

    public void addWeight(Integer newWeight) {
        Preconditions.checkArgument(newWeight <= payloadCapacity - this.weight, "too heavy weight");
        this.weight += newWeight;
    }

    @Override
    public String toString() {
        return "Cargo: \nnumber " + super.getNumber()
                + "\npayloadCapacity " + payloadCapacity + "\nweight " + weight;
    }
}
