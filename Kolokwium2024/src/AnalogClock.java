import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AnalogClock extends Clock {
    private final List<ClockHand> hands;
    public AnalogClock(City city){
        super(city);
        hands=new ArrayList<>();
        hands.add(new HourHand());
        hands.add(new MinuteHand());
        hands.add(new SecondHand());
    }
    public void setTime(LocalTime time){
        for(ClockHand hand : hands){
            hand.setTime(time);
        }
    }
    public void toSvg(String filepath) {
        String safeFilePath = filepath.replace(":", "-");
           File file = new File(safeFilePath);
        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
            PrintWriter out = null;
           try {
               out=new PrintWriter(file);
               out.println("<svg width=\"200\" height=\"200\" viewBox=\"-100 -100 200 200\" xmlns=\"http://www.w3.org/2000/svg\">");
               out.println("  <circle cx=\"0\" cy=\"0\" r=\"90\" fill=\"none\" stroke=\"black\" stroke-width=\"2\" />");
               out.println("  <g text-anchor=\"middle\">");
               out.println("    <text x=\"0\" y=\"-80\" dy=\"6\">12</text>");
               out.println("    <text x=\"80\" y=\"0\" dy=\"4\">3</text>");
               out.println("    <text x=\"0\" y=\"80\" dy=\"6\">6</text>");
               out.println("    <text x=\"-80\" y=\"0\" dy=\"4\">9</text>");
               out.println("  </g>");
               for(ClockHand hand : hands) {
                   out.println(hand.toSvg());
               }
               out.println("</svg>");
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }
           finally {
               if(out!= null) {
                   out.close();
               }
           }
    }

}
