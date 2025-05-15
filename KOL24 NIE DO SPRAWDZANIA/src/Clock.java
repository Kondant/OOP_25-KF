import java.time.LocalTime;

public abstract class Clock {
    // Pole przechowujące aktualny czas zegara
    private LocalTime time;

    // Obiekt miasta, dla którego zegar jest ustawiony
    private City city;

    // Ustawia czas zegara na aktualny systemowy czas (LocalTime.now())
    public void setCurrentTime() {
        this.time = LocalTime.now();
    }

    // Ustawia czas zegara na podaną godzinę, minuty i sekundy
    // Weryfikuje poprawność wartości, wyrzuca wyjątek jeśli jest niepoprawna
    public void setTime(int h, int m, int s) {
        if (h < 0 || h > 23) {
            throw new IllegalArgumentException();
        }
        if (m < 0 || m > 59) {
            throw new IllegalArgumentException();
        }
        if (s < 0 || s > 59) {
            throw new IllegalArgumentException();
        }
        this.time = LocalTime.of(h, m, s);
    }

    // Nadpisanie metody toString, aby zwrócić czas w formacie HH:mm:ss
    public String toString() {
        return String.format("%02d:%02d:%02d", time.getHour(), time.getMinute(), time.getSecond());
    }

    // Ustawia nowe miasto dla zegara i aktualizuje czas na podstawie różnicy stref czasowych
    public void setCity(City city) {
        // Jeśli czas nie został wcześniej ustawiony, ustaw go na aktualny
        if (time == null) {
            setCurrentTime();
        }

        // Pobieramy starą i nową strefę czasową (zakładam, że int reprezentuje przesunięcie godzinowe)
        int oldZone = this.city.getTimezone();
        int newZone = city.getTimezone();

        // Obliczamy różnicę godzin pomiędzy strefami
        int hours = newZone - oldZone;

        // Aktualizujemy czas o tę różnicę
        time = time.plusHours(hours);

        // Korekta czasu, jeśli wyszedł poza zakres 0-23 godzin
        if (time.getHour() < 0) {
            time = time.plusHours(24);  // dodajemy 24 godziny, by wrócić do poprawnego zakresu
        } else if (time.getHour() >= 24) {
            time = time.minusHours(24); // odejmujemy 24 godziny, by wrócić do poprawnego zakresu
        }

        // Ustawiamy nowe miasto
        this.city = city;
    }

    // Konstruktor klasy Clock ustawiający początkowe miasto i aktualny czas
    public Clock(City city) {
        this.city = city;
        setCurrentTime();
    }

    // Getter zwracający aktualny czas zegara
    public LocalTime getTime() {
        return time;
    }
}
