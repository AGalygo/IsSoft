import java.util.Objects;

public abstract class Carriage {
    private String number;
    private Carriage next;

    public Carriage(String number) {
        this.number = number;

    }

    public Carriage getNext() {
        return this.next;
    }

    public void setNext(Carriage carriage) {
        this.next = carriage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carriage)) return false;
        Carriage carriage = (Carriage) o;
        return getNumber().equals(carriage.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }

    public String getNumber() {
        return this.number;
    }
}
