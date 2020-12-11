package calls;

import entity.Human;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CallCreator implements Runnable {

    private boolean stopped;
    private final Integer MAX_FLOOR;

    Calls tasks = Calls.getInstance();

    public String[] names = {"Alena", "Jon", "Natalia", "Peter", "Dima", "Maksim", "Julia", "Alex", "Gleb", "Alisa", "Yauheni", "Andrey",
            "Nastya", "Kirill", "Anton"};

    public CallCreator(Integer maxFloor) {
        this.stopped = false;
        this.MAX_FLOOR = maxFloor;
    }

    @Override
    public void run() {
        init();
    }

    @SneakyThrows
    public void init() {
        Random random = new Random();
        String name;
        Integer weight;
        Integer boardingFloor;
        Integer destinationFloor;

        while (!stopped) {
            name = names[random.nextInt(names.length)];
            weight = random.nextInt(100) + 35;
            boardingFloor = random.nextInt(MAX_FLOOR - 1) + 1;
            do {
                destinationFloor = random.nextInt(MAX_FLOOR - 1) + 1;
            }
            while (boardingFloor == destinationFloor);
            Human passenger = Human.of(name, weight, boardingFloor, destinationFloor);
            tasks.addCall(passenger);
            log.info("Passenger " + passenger.toString() + " is waiting the elevator. \nTotal waiting passengers: " + tasks.size());
            TimeUnit.SECONDS.sleep(random.nextInt(20));
        }
    }

    public void stop() {
        this.stopped = true;
    }
}
