import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Klasa reprezentująca miasto
public class City {
    private String name;       // Nazwa miasta
    private int Timezone;      // Strefa czasowa w formacie GMT offset (np. +1)
    private String szer;       // Szerokość geograficzna w formacie "wartość kierunek" (np. "52.23 N")
    private String dlug;       // Długość geograficzna w formacie "wartość kierunek" (np. "21.01 E")

    // Konstruktor klasy City
    public City(String name, int Timezone, String szer, String dlug) {
        this.name = name;
        this.Timezone = Timezone;
        this.szer = szer;
        this.dlug = dlug;
    }

    // Metoda parsująca linię tekstu na obiekt City
    private static City parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Zły format linii");
        }
        String name = parts[0];
        int Timezone = Integer.parseInt(parts[1]);
        String szer = parts[2];
        String dlug = parts[3];
        City city = new City(name, Timezone, szer, dlug);
        return city;
    }

    // Metoda wczytująca dane z pliku CSV i zwracająca mapę miast
    public static Map<String, City> parseFile(String filePath) {
        Map<String, City> cities = new HashMap<>();
        try {
            File file = new File(filePath);
            Scanner in = new Scanner(file);
            if (in.hasNextLine()) {
                in.nextLine(); // Pomijamy nagłówek
            }
            while (in.hasNextLine()) {
                String line = in.nextLine();
                City city = parseLine(line);
                cities.put(city.getName(), city);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

    // Metoda przeliczająca długość geograficzną na liczbę zmiennoprzecinkową
    private double zmianaDlug(String dlug) {
        String[] parts = dlug.trim().split(" ");
        double wspol = Double.parseDouble(parts[0]);
        String kierunek = parts[1];
        if (kierunek.equalsIgnoreCase("W")) {
            wspol = -wspol; // Jeśli kierunek to "W", zmieniamy znak
        }
        return wspol;
    }

    // Metoda przeliczająca szerokość geograficzną na liczbę zmiennoprzecinkową
    private double zmianaSzer(String szer) {
        String[] parts = szer.trim().split(" ");
        double wspol = Double.parseDouble(parts[0]);
        String kierunek = parts[1];
        if (kierunek.equalsIgnoreCase("S")) {
            wspol = -wspol; // Jeśli kierunek to "S", zmieniamy znak
        }
        return wspol;
    }

    // Oblicza lokalny czas słoneczny (Local Mean Time) na podstawie długości geograficznej
    public LocalTime localMeanTime(LocalTime time) {
        double dlugValue = zmianaDlug(this.dlug);
        double przesGodz = (dlugValue / 180.0) * 12; // Przeliczanie długości geograficznej na różnicę godzin
        int sekundyDoDod = (int) (przesGodz * 60 * 60); // Zamiana godzin na sekundy
        return time.plusSeconds(sekundyDoDod).withNano(0); // Dodanie do czasu i usunięcie nanosekund
    }

    // Porównuje dwa miasta i zwraca, które ma większe odchylenie od rzeczywistego czasu lokalnego
    public static int worstTimezoneFit(City city1, City city2) {
        int timeDiff1 = Math.abs(city1.getTimezone() - city1.localMeanTime(LocalTime.of(12, 0)).getHour());
        int timeDiff2 = Math.abs(city2.getTimezone() - city2.localMeanTime(LocalTime.of(12, 0)).getHour());
        return Integer.compare(timeDiff2, timeDiff1); // Większe odchylenie = gorsze dopasowanie
    }

    // Generuje pliki SVG zegarów analogowych dla listy miast
    public static void generateAnalogClocksSvg(List<City> cities, AnalogClock clock) {
        String directoryName = clock.toString(); // Nazwa katalogu zależna od typu zegara
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs(); // Tworzy katalog jeśli nie istnieje
        }
        for (City city : cities) {
            clock.setCity(city); // Ustawia miasto w zegarze
            String fileName = city.getName() + ".svg";
            File svgFile = new File(directory, fileName);
            clock.toSvg(svgFile.getAbsolutePath()); // Zapisuje plik SVG
        }
    }

    // Gettery do pól prywatnych
    public String getDlug() {
        return dlug;
    }

    public String getName() {
        return name;
    }

    public String getSzer() {
        return szer;
    }

    public int getTimezone() {
        return Timezone;
    }
}
