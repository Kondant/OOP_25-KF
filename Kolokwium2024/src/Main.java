import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\konra\\Desktop\\kolokwium1_2024\\strefy.csv";
        Map<String,City> cities = City.parseFile(filePath);
        City city= cities.get("Warszawa");
        DigitalClock clock = new DigitalClock(DigitalClock.ClockType.H12,city);
        clock.setTime(12, 12, 2);
        clock.setCurrentTime();
        System.out.println("Czas: " + clock);
        List<City> citiesList = new ArrayList<>(cities.values());
        for (Map.Entry<String, City> entry : cities.entrySet()) {
            String cityName = entry.getKey();
            city = entry.getValue();
            System.out.println("Miasto: " + cityName + " Strefa czasowa: " + city.getTimezone()
                    + " Szerokość geograficzna: " + city.getSzer() + " Długość geograficzna: " + city.getDlug());
            clock.setCity(city);
            System.out.println("Czas po zmianie " + cityName + ": " + clock);
            LocalTime czasStref = clock.getTime();
            LocalTime czasLok = city.localMeanTime(czasStref);
            System.out.println("Czas lokalny w " + cityName + ": " + czasLok);
        }
        citiesList.sort(City::worstTimezoneFit);
        System.out.println("\nMiasta posortowane wedlug worstTimezoneFit: ");
        for(City c : citiesList) {
            System.out.println(c.getName());
        }
        AnalogClock aClock = new AnalogClock(city);
        aClock.setCurrentTime();
        aClock.setTime(aClock.getTime());
        aClock.toSvg("C:\\Users\\konra\\Desktop\\kolokwium1_2024\\zegar.svg");
        City.generateAnalogClocksSvg(citiesList, aClock);
    }
}

