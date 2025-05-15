import java.util.List;

public class LandTest {
    public static void main(String[] args) {
        Land land = new Land(List.of(
                new Point(0, 0),
                new Point(0, 10),
                new Point(10, 10),
                new Point(10, 0)
        ));

        City outsideCity = new City("Atlantis", new Point(20, 20), 4.0);

        try {
            land.addCity(outsideCity);
            System.out.println("❌ Test NIEZALICZONY – wyjątek nie został rzucony.");
        } catch (RuntimeException e) {
            if ("Atlantis".equals(e.getMessage())) {
                System.out.println("✅ Test ZALICZONY – poprawna nazwa miasta w wyjątku.");
            } else {
                System.out.println("❌ Test NIEZALICZONY – błędna wiadomość: " + e.getMessage());
            }
        }
    }
}
