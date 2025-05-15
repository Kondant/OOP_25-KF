import java.time.LocalTime;

// Klasa abstrakcyjna reprezentująca wskazówkę zegara
public abstract class ClockHand {

    // Abstrakcyjna metoda do ustawiania czasu dla wskazówki.
    // Każda wskazówka (godzinowa, minutowa, sekundowa) implementuje tę metodę,
    // aby ustawić swój kąt lub pozycję na podstawie podanego czasu.
    public abstract void setTime(LocalTime time);

    // Abstrakcyjna metoda generująca reprezentację SVG wskazówki.
    // Każda wskazówka zwraca swój fragment SVG, który można narysować w pliku.
    public abstract String toSvg();
}
