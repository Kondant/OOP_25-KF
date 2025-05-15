import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AnalogClock extends Clock {
    // Lista wskazówek zegara (godzinowa, minutowa, sekundowa)
    private final List<ClockHand> hands;

    // Konstruktor, który przyjmuje miasto i tworzy trzy wskazówki
    public AnalogClock(City city) {
        super(city);  // wywołanie konstruktora klasy nadrzędnej Clock
        hands = new ArrayList<>();
        hands.add(new HourHand());    // dodaj wskazówkę godzinową
        hands.add(new MinuteHand());  // dodaj wskazówkę minutową
        hands.add(new SecondHand());  // dodaj wskazówkę sekundową
    }

    // Ustawia czas dla wszystkich wskazówek na podstawie przekazanego LocalTime
    public void setTime(LocalTime time) {
        for (ClockHand hand : hands) {
            hand.setTime(time);
        }
    }

    // Metoda zapisująca reprezentację zegara analogowego do pliku SVG
    public void toSvg(String filepath) {
        // Zabezpieczenie ścieżki pliku – zamiana dwukropków na myślniki (problemy z nazwami plików)
        String safeFilePath = filepath.replace(":", "-");

        // Tworzymy plik i sprawdzamy czy katalog istnieje, jeśli nie to go tworzymy
        File file = new File(safeFilePath);
        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(file);

            // Pisanie nagłówka SVG, definiujemy widok i rozmiar
            out.println("<svg width=\"200\" height=\"200\" viewBox=\"-100 -100 200 200\" xmlns=\"http://www.w3.org/2000/svg\">");

            // Rysujemy obwód tarczy zegara (okrąg)
            out.println("  <circle cx=\"0\" cy=\"0\" r=\"90\" fill=\"none\" stroke=\"black\" stroke-width=\"2\" />");

            // Rysujemy cyfry 12, 3, 6, 9 na tarczy w odpowiednich pozycjach
            out.println("  <g text-anchor=\"middle\">");
            out.println("    <text x=\"0\" y=\"-80\" dy=\"6\">12</text>");
            out.println("    <text x=\"80\" y=\"0\" dy=\"4\">3</text>");
            out.println("    <text x=\"0\" y=\"80\" dy=\"6\">6</text>");
            out.println("    <text x=\"-80\" y=\"0\" dy=\"4\">9</text>");
            out.println("  </g>");

            // Rysujemy wszystkie wskazówki zegara, każda generuje swój fragment SVG przez metodę toSvg()
            for (ClockHand hand : hands) {
                out.println(hand.toSvg());
            }

            // Zamykamy znacznik SVG
            out.println("</svg>");

        } catch (FileNotFoundException e) {
            // Obsługa wyjątku, jeśli plik nie może zostać utworzony lub zapisany
            e.printStackTrace();

        } finally {
            // Zamykamy strumień zapisu, aby zwolnić zasoby
            if (out != null) {
                out.close();
            }
        }
    }
}
