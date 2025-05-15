import java.util.List;

public class CityTest {
    public static void main(String[] args) {
        // Miasto nieportowe w centrum lądu
        City inlandCity = new City("Inland", new Point(5, 5), 2);
        inlandCity.setPortStatus(new Land(List.of( // ląd wkoło
                new Point(0, 0), new Point(0, 10),
                new Point(10, 10), new Point(10, 0)
        )));

        // Miasto portowe - jeden narożnik poza lądem
        City portCity = new City("Port", new Point(9, 9), 4);
        portCity.setPortStatus(new Land(List.of(
                new Point(0, 0), new Point(0, 10),
                new Point(10, 10), new Point(10, 0)
        )));

        // Zasoby
        Resource coalNear = new Resource(new Point(5.5, 5.5), Resource.Type.Coal);
        Resource woodFar = new Resource(new Point(20, 20), Resource.Type.Wood);
        Resource fishNear = new Resource(new Point(10.5, 10.5), Resource.Type.Fish);

        double range = 2.0;

        // TEST 1
        inlandCity.addResourcesInRange(List.of(coalNear), range);
        assertTest("Dodanie węgla", inlandCity.resources.contains(Resource.Type.Coal));

        // TEST 2
        inlandCity.addResourcesInRange(List.of(woodFar), range);
        assertTest("Odrzucenie drewna spoza zasięgu", !inlandCity.resources.contains(Resource.Type.Wood));

        // TEST 3
        portCity.addResourcesInRange(List.of(fishNear), range);
        assertTest("Dodanie ryb do portowego", portCity.resources.contains(Resource.Type.Fish));

        // TEST 4
        inlandCity.addResourcesInRange(List.of(fishNear), range);
        assertTest("Odrzucenie ryb dla śródlądowego", !inlandCity.resources.contains(Resource.Type.Fish));
    }

    private static void assertTest(String description, boolean passed) {
        if (passed) {
            System.out.println("✅ " + description + " – ZALICZONY");
        } else {
            System.out.println("❌ " + description + " – NIEZALICZONY");
        }
    }
}
