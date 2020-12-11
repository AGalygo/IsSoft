package calls;

import Enum.Direction;
import entity.Human;
import lombok.Getter;
import lombok.Setter;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Calls {

    private Deque<Human> calls;
    public static volatile Calls INSTANCE;
    private final Lock lock;

    public static Calls getInstance() {
        if (Calls.INSTANCE == null) {
            synchronized (Calls.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Calls();
                }
            }
        }
        return INSTANCE;
    }

    private Calls() {
        calls = new LinkedList<>();
        this.lock = new ReentrantLock();
    }

    public void addCall(Human human) {
        lock.lock();
        calls.add(human);
        lock.unlock();
    }

    public Human removeCall() {
        lock.lock();
        Human call;
        if (!calls.isEmpty()) {
            call = calls.poll();
        } else {
            call = null;
        }
        lock.unlock();
        return call;

    }

    public Human findCalls(Direction direction, Integer floor, Integer capacity) {
        lock.lock();
        Optional<Human> call = calls.stream().filter(h -> h.getDirection() == direction && h.getBoardingFloor() == floor).findFirst();
        if (call.isPresent() && call.get().getWeight() < capacity) {
            calls.removeFirstOccurrence(call.get());
            lock.unlock();
            return call.get();
        }
        lock.unlock();
        return null;
    }

    public Integer size() {
        return calls.size();
    }

}
