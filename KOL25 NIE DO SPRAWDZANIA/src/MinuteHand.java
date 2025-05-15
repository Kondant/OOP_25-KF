import java.time.LocalTime;
import java.util.Locale;

public class MinuteHand extends ClockHand {
    // Kąt obrotu wskazówki minutowej w stopniach
    private double angle;

    // Ustawia kąt wskazówki minutowej na podstawie minut i sekund czasu
    public void setTime(LocalTime time) {
        // Każda minuta to 6 stopni (360° / 60 minut)
        // Dodatkowo uwzględniamy przesunięcie o sekundy,
        // ponieważ wskazówka minutowa porusza się płynnie (0.1 stopnia na sekundę)
        angle = time.getMinute() * 6 + time.getSecond() * 0.1;
    }

    // Generuje fragment SVG dla wskazówki minutowej
    public String toSvg() {
        // Rysuje czarną linię o grubości 2 od środka (0,0) do y = -70 (do góry)
        // Obraca linię o obliczony kąt angle
        return String.format(Locale.US,
                "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-70\" stroke=\"black\" stroke-width=\"2\" transform=\"rotate(%.1f)\" />",
                angle);
    }
}
