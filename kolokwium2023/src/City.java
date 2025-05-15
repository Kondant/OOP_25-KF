import java.util.*;

public class City extends Polygon {
    public final Point center;
    private String name;
    private boolean port; // dodane w kroku 9
    Set<Resource.Type> resources = new HashSet<>(); // pakietowy dostep

    public City(String name, Point center, double wallLength) {
        super(generateSquare(center, wallLength));
        this.center = center;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isPort() {
        return port;
    }

    public void setPortStatus(Land land) {
        for (Point p : getPoints()) {
            if (!land.inside(p)) {
                this.port = true;
                return;
            }
        }
        this.port = false;
    }

    public void addResourcesInRange(List<Resource> resourceList, double range) {
        for (Resource res : resourceList) {
            double distance = Math.sqrt(Math.pow(res.location.a - center.a, 2) + Math.pow(res.location.b - center.b, 2));

            if (distance <= range) {
                if (res.type == Resource.Type.Fish && !port) continue;
                resources.add(res.type);
            }
        }
    }

    private static List<Point> generateSquare(Point center, double length) {
        double half = length / 2;
        return List.of(
                new Point(center.a - half, center.b - half),
                new Point(center.a - half, center.b + half),
                new Point(center.a + half, center.b + half),
                new Point(center.a + half, center.b - half)
        );
    }
}