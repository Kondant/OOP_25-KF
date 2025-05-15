import java.util.ArrayList;
import java.util.List;

public class Land extends Polygon{
    private List<City> cities = new ArrayList<>();

    public void addCity(City city) {
        if (inside(city.center)) {
            cities.add(city);
        } else {
            throw new RuntimeException(city.getName());
        }
        city.setPortStatus(this);
    }

    public Land(List<Point> pointList) {
        super(pointList);
    }
}
