import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Ścieżka do pliku CSV z danymi o miastach i strefach czasowych
        String filePath = "C:\\Users\\konra\\Desktop\\kolokwium1_2024\\strefy.csv";

        // Parsowanie pliku i stworzenie mapy miast (klucz: nazwa miasta, wartość: obiekt City)
        Map<String, City> cities = City.parseFile(filePath);

        // Pobranie obiektu City dla Warszawy
        City city = cities.get("Warszawa");

        // Utworzenie cyfrowego zegara w formacie 12-godzinnym, ustawionego dla Warszawy
        DigitalClock clock = new DigitalClock(DigitalClock.ClockType.H12, city);

        // Ręczne ustawienie czasu na 12:12:02
        clock.setTime(12, 12, 2);

        // Ustawienie bieżącego czasu na zegarze (aktualizacja wewnętrznego stanu zegara)
        clock.setCurrentTime();

        // Wyświetlenie aktualnego czasu na zegarze
        System.out.println("Czas: " + clock);

        // Stworzenie listy miast na podstawie wartości w mapie (łatwiejsze do iteracji)
        List<City> citiesList = new ArrayList<>(cities.values());

        // Iteracja po wszystkich wpisach w mapie miast
        for (Map.Entry<String, City> entry : cities.entrySet()) {
            String cityName = entry.getKey();     // Nazwa miasta
            city = entry.getValue();               // Obiekt City dla danego miasta

            // Wyświetlenie informacji o mieście: nazwa, strefa czasowa, szerokość i długość geograficzna
            System.out.println("Miasto: " + cityName + " Strefa czasowa: " + city.getTimezone()
                    + " Szerokość geograficzna: " + city.getSzer() + " Długość geograficzna: " + city.getDlug());

            // Ustawienie zegara na aktualne miasto
            clock.setCity(city);

            // Wyświetlenie czasu na zegarze po zmianie miasta
            System.out.println("Czas po zmianie " + cityName + ": " + clock);

            // Pobranie czasu w strefie czasowej miasta
            LocalTime czasStref = clock.getTime();

            // Obliczenie czasu lokalnego dla miasta (np. uwzględniając długość geograficzną)
            LocalTime czasLok = city.localMeanTime(czasStref);

            // Wyświetlenie czasu lokalnego w danym mieście
            System.out.println("Czas lokalny w " + cityName + ": " + czasLok);
        }

        // Sortowanie listy miast według kryterium worstTimezoneFit (zakładam, że jest to Comparator)
        citiesList.sort(City::worstTimezoneFit);

        System.out.println("\nMiasta posortowane wedlug worstTimezoneFit: ");

        // Wyświetlenie nazw miast po posortowaniu
        for (City c : citiesList) {
            System.out.println(c.getName());
        }

        // Utworzenie analogowego zegara dla ostatnio ustawionego miasta (np. Warszawy)
        AnalogClock aClock = new AnalogClock(city);

        // Ustawienie aktualnego czasu na analogowym zegarze
        aClock.setCurrentTime();

        // Ustawienie czasu na zegarze na jego aktualny czas (może wywołanie synchronizujące lub odświeżające)
        aClock.setTime(aClock.getTime());

        // Zapisanie obrazu SVG zegara do pliku
        aClock.toSvg("C:\\Users\\konra\\Desktop\\kolokwium1_2024\\zegar.svg");

        // Wygenerowanie SVG z zegarami analogowymi dla wszystkich miast na podstawie istniejącego zegara
        City.generateAnalogClocksSvg(citiesList, aClock);
    }
}
