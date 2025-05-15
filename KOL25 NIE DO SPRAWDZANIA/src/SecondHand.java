import java.time.LocalTime;
import java.util.Locale;

public class SecondHand extends ClockHand {
  // Kąt obrotu wskazówki sekundowej w stopniach
  private double angle;

  // Metoda ustawia kąt wskazówki na podstawie sekund z podanego czasu
  public void setTime(LocalTime time) {
    // Każda sekunda to 6 stopni (360° / 60 sekund)
    angle = time.getSecond() * 6;
  }

  // Generuje fragment SVG reprezentujący wskazówkę sekundową
  public String toSvg() {
    // Tworzy linię od środka zegara (0,0) do punktu y = -80 (do góry),
    // obracając ją o kąt angle w stopniach
    // Wskazówka jest czerwona i ma grubość 1
    return String.format(Locale.US,
            "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-80\" stroke=\"red\" stroke-width=\"1\" transform=\"rotate(%.1f)\" />",
            angle);
  }
}
