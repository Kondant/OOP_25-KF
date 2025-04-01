public class Style {
    public final String fillColor,strokeColor;
    public final Double strokeWidth;

    public Style(String fillColor, String strokeColor, Double strokeWidth) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }
    public String toSvg()
    {
        return "style=\"fill: "+fillColor+";"
                +"strokeColor: "+strokeColor+";"
                + "strokeWidth: "+strokeWidth+ "\"";
    }
}
