import java.time.LocalTime;
import java.util.Locale;

public class HourHand extends ClockHand{
    private double angle;
    public void setTime(LocalTime time){
        angle= (time.getHour()%12)*30+time.getMinute()*0.5+time.getSecond()*(0.5/60);
    }
    public String toSvg(){
        return String.format(Locale.US,"<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-50\" stroke=\"black\" stroke-width=\"4\" transform=\"rotate(%.1f)\" />",
                angle);
    }
}
