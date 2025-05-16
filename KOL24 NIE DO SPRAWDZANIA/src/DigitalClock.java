// Klasa reprezentująca zegar cyfrowy, dziedzicząca po klasie Clock
public class DigitalClock extends Clock {

    // Typ wyświetlania czasu: 24-godzinny (H24) lub 12-godzinny (H12)
    public enum ClockType {
        H24, H12;
    }

    private ClockType type; // Pole przechowujące typ zegara

    // Konstruktor przyjmujący typ zegara oraz obiekt miasta
    public DigitalClock(ClockType type, City city) {
        super(city); // Wywołuje konstruktor klasy nadrzędnej Clock z miastem
        this.type = type;
    }

    // Konstruktor domyślny ustawiający zegar 24-godzinny
    public DigitalClock(City city) {
        super(city);
        this.type = ClockType.H24;
    }

    // Nadpisanie metody toString() - zwraca aktualny czas w odpowiednim formacie
    public String toString() {
        // Jeśli zegar jest w trybie 24-godzinnym, użyj implementacji nadrzędnej klasy
        if (type == ClockType.H24) {
            return super.toString();
        } else {
            // Pobranie godziny z obiektu czasu
            int hour = getTime().getHour();
            
            // Formatowanie czasu w trybie 12-godzinnym
            if (hour > 12) {
                hour = hour - 12;
                return String.format("%d:%02d:%02d PM", hour, getTime().getMinute(), getTime().getSecond());
            } else if (hour == 0) {
                hour = 12;
                return String.format("%d:%02d:%02d AM", hour, getTime().getMinute(), getTime().getSecond());
            } else if (hour == 12) {
                return String.format("%d:%02d:%02d PM", hour, getTime().getMinute(), getTime().getSecond());
            } else {
                return String.format("%d:%02d:%02d AM", hour, getTime().getMinute(), getTime().getSecond());
            }
        }
    }
}
