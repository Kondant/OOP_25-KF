import java.util.List;
//zip -r nazwa_archiwum.zip katalog_do_spakowania
public class Main {
    public static void main(String[] args) {
        List<Point> polygonPoints = List.of(
                new Point(0, 0),
                new Point(0, 5),
                new Point(5, 5),
                new Point(5, 0)
        );

        Polygon polygon = new Polygon(polygonPoints);

        Point pointInside = new Point(2, 2);
        Point pointBelow = new Point(2, -1);
        Point pointRight = new Point(6, 2);

        test("Wewnątrz", polygon.inside(pointInside), true);
        test("Poniżej", polygon.inside(pointBelow), false);
        test("Z prawej", polygon.inside(pointRight), false);
    }

    private static void test(String description, boolean actual, boolean expected) {
        if (actual == expected) {
            System.out.println("Test '" + description + "' zaliczony.");
        } else {
            System.out.println("Test '" + description + "' NIEZALICZONY. Oczekiwano: " + expected + ", otrzymano: " + actual);
        }
    }
}
/*
KROK 14: addCitiesToLands

// W klasie MapParser implementujemy metodę, która dla każdego lądu doda miasta,
// których środek znajduje się na tym lądzie.
// Przykład implementacji:

public void addCitiesToLands() {
    for (Land land : lands) {                   // Iterujemy po wszystkich lądach
        for (City city : cities) {              // Iterujemy po wszystkich miastach
            if (land.inside(city.center)) {    // Sprawdzamy, czy środek miasta jest na lądzie
                land.addCity(city);             // Dodajemy miasto do listy miast tego lądu
            }
        }
    }
}

// Uwaga:
// - metoda inside(Point) powinna być dostępna w klasie Land (dziedziczy po Polygon).
// - metoda addCity(City) powinna istnieć w klasie Land i dodawać miasto do prywatnej listy miast.
// - dzięki temu miasta, które są „na lądzie”, trafią do odpowiednich lądów.
*/

/*
KROK 15: matchLabelsToTowns

// Metoda przypisująca miastom nazwy na podstawie najbliższego labela (z listy <text> z pliku SVG).

public void matchLabelsToTowns() {
    for (City city : cities) {
        Label closestLabel = null;
        double minDistance = Double.MAX_VALUE;

        for (Label label : labels) {
            double distance = distanceBetween(city.center, label.position);
            if (distance < minDistance) {
                minDistance = distance;
                closestLabel = label;
            }
        }

        if (closestLabel != null) {
            city.setName(closestLabel.getText());  // ustawiamy nazwę miasta z labela
        }
    }
}

// Pomocnicza metoda do liczenia odległości między dwoma punktami

private double distanceBetween(Point p1, Point p2) {
    double dx = p1.a - p2.a;
    double dy = p1.b - p2.b;
    return Math.sqrt(dx*dx + dy*dy);
}

// Uwaga:
// - metoda setName(String) musi być publiczna w klasie City, aby przypisać nazwę miasta.
// - dzięki tej metodzie miasta z listy cities otrzymają nazwy z pliku SVG.
*/

/*
KROK 16: Main i metody toString

// W klasie Main tworzymy MapParser i wywołujemy metodę parse,
// aby wczytać dane z pliku map.svg.

public static void main(String[] args) {
    MapParser parser = new MapParser();
    parser.parse("map.svg");  // parsowanie pliku SVG

    // Po parsowaniu mamy listy lądów i miast.

    // Następnie możemy wyświetlić miasta na każdym lądzie:
    for (Land land : parser.getLands()) {
        System.out.println(land.toString());  // wywołuje toString lądu
    }
}

// Metoda toString w klasie City:

@Override
public String toString() {
    // Jeśli miasto portowe, dodaj symbol ⚓
    return name + (isPort ? "⚓" : "");
}

// Metoda toString w klasie Land:

@Override
public String toString() {
    // Zwraca nazwy wszystkich miast na lądzie połączone przecinkami
    return cities.stream()
                 .map(City::toString)
                 .collect(Collectors.joining(", "));
}

// Uwaga:
// - isPort to prywatne pole boolean w klasie City, które mówi, czy miasto jest portowe.
// - toString pozwala na czytelne wyświetlenie miasta i jego statusu portowego.
// - dzięki temu na konsoli zobaczymy listę miast z mapy z symbolem portu, np.:
//   "Kryształowiec, Złoty Horyzont⚓"
// - Miasta, których centra są na wodzie, nie zostaną przypisane do żadnego lądu i nie pojawią się w wynikach.
*/

