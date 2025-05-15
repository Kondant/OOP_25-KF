import java.time.LocalTime;
import java.util.Locale;

public class HourHand extends ClockHand {
    // Kąt obrotu wskazówki godzinowej w stopniach
    private double angle;

    // Ustawia kąt wskazówki godzinowej na podstawie godziny, minut i sekund
    public void setTime(LocalTime time) {
        // Godzina na zegarze 12-godzinnym: każda godzina to 30 stopni (360° / 12)
        // Dodajemy kąt wynikający z minut: wskazówka godzinowa przesuwa się o 0.5° na minutę (30°/60)
        // Dodajemy też przesunięcie sekundowe: 0.5°/60 sekund, czyli bardzo płynny ruch
        angle = (time.getHour() % 12) * 30 + time.getMinute() * 0.5 + time.getSecond() * (0.5 / 60);
    }

    // Generuje fragment SVG dla wskazówki godzinowej
    public String toSvg() {
        // Rysuje czarną linię o grubości 4 od środka (0,0) do y = -50 (do góry)
        // Obraca linię o wyliczony kąt angle
        return String.format(Locale.US,
                "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-50\" stroke=\"black\" stroke-width=\"4\" transform=\"rotate(%.1f)\" />",
                angle);
    }
}
