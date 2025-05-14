import java.time.LocalTime;
import java.util.Locale;

public class SecondHand extends ClockHand{
  private double angle;
  public void setTime(LocalTime time){
  angle=time.getSecond()*6;
  }
  public String toSvg(){
    return String.format(Locale.US,"<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-80\" stroke=\"red\" stroke-width=\"1\" transform=\"rotate(%.1f)\" />",
            angle);
  }
}
