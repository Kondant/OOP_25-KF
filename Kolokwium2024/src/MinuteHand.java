import java.time.LocalTime;
import java.util.Locale;

public class MinuteHand extends ClockHand{
    private double angle;
    public void setTime(LocalTime time){
        angle=time.getMinute()*6+time.getSecond()*0.1;
    }
    public String toSvg(){
        return String.format(Locale.US,"<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-70\" stroke=\"black\" stroke-width=\"2\" transform=\"rotate(%.1f)\" />",
                angle);
    }
}

