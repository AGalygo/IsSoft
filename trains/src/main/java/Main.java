

public class Main {


    public static void main(String[] args) {
        Train train = Train.of("Minsk", "Grodno");
        Locomotive locomotive = Locomotive.ofNumber();
        locomotive.setDriver(Driver.of("Andrey", "Shihutin", 34));
        train.addCarriage(locomotive);

        Passengers passengers = Passengers.of(35);
        passengers.addPassenger(Person.of("first", "passenger"));
        passengers.addPassenger(Person.of("second", "passenger"));
        passengers.addPassenger(Person.of("third", "passenger"));
        passengers.addPassenger(Person.of("fourth", "passenger"));
        passengers.addPassenger(Person.of("fifth", "passenger"));
        train.addCarriage(passengers);

        Cargo cargo = Cargo.of(200000);
        cargo.addWeight(20000);
        cargo.addWeight(100000);
        train.addCarriage(cargo);

        System.out.println(train.toString());


    }
}
