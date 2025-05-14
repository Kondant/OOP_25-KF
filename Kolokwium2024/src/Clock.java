import java.time.LocalTime;

public abstract class Clock {
    private LocalTime time;
    private City city;
    public void setCurrentTime(){
        this.time=LocalTime.now();
    }
    public void setTime(int h, int m, int s) {
      if(h<0 || h>23)
      {throw new IllegalArgumentException();}
      if(m<0 || m>59)
      {throw new IllegalArgumentException();}
      if(s<0 || s>59)
      {throw new IllegalArgumentException();}
        this.time = LocalTime.of(h, m, s);
    }
    public String toString() {
        return String.format("%02d:%02d:%02d", time.getHour(), time.getMinute(), time.getSecond());
    }
    public void setCity(City city){
        if(time==null){
            setCurrentTime();
        }
    int oldZone=this.city.getTimezone();
    int newZone=city.getTimezone();
    int hours=newZone-oldZone;
    time=time.plusHours(hours);
        if (time.getHour() < 0) {
            time = time.plusHours(24);
        } else if (time.getHour() >= 24) {
            time = time.minusHours(24);
        }
    this.city=city;

    }
    public Clock(City city) {
        this.city = city;
        setCurrentTime();
    }

    public LocalTime getTime() {
        return time;
    }
}