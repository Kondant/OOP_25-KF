import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class MapParser {
    private List<Land> lands = new ArrayList<>();
    private List<City> cities = new ArrayList<>();
    NodeList polygonNodes = document.getElementsByTagName("polygon");
for (int i = 0; i < polygonNodes.getLength(); i++) {
        Element polygonElement = (Element) polygonNodes.item(i);
        String fill = polygonElement.getAttribute("fill");
        if ("green".equalsIgnoreCase(fill)) {
            String pointsAttr = polygonElement.getAttribute("points");
            List<Point> points = parsePoints(pointsAttr);
            Land land = new Land(points);
            lands.add(land);
        }
    }
    private List<Point> parsePoints(String pointsAttr) {
        List<Point> points = new ArrayList<>();
        String[] pairs = pointsAttr.trim().split("\\s+");
        for (String pair : pairs) {
            String[] coords = pair.split(",");
            double x = Double.parseDouble(coords[0]);
            double y = Double.parseDouble(coords[1]);
            points.add(new Point(x, y));
        }
        return points;
    }
    public List<Land> getLands() {
        return lands;
    }

    public List<City> getCities() {
        return cities;
    }
}
