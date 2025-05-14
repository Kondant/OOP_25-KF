public class DigitalClock extends Clock {

    public enum ClockType {
        H24, H12;
    }

    private ClockType type;

public DigitalClock(ClockType type, City city) {
    super(city);
        this.type = type;
    }
    public DigitalClock(City city) {
    super(city);
        this.type = ClockType.H24;
    }
    public String toString(){
    if(type==ClockType.H24){
        return super.toString();
    }
    else
    {
        int hour = getTime().getHour();
        if(hour>12)
        {
            hour=hour-12;
            return String.format("%d:%02d:%02d PM",hour,getTime().getMinute(),getTime().getSecond());
        }else if(hour==0)
        {
            hour=12;
            return String.format("%d:%02d:%02d AM",hour,getTime().getMinute(),getTime().getSecond());

        }else if(hour==12){

            return String.format("%d:%02d:%02d PM",hour,getTime().getMinute(),getTime().getSecond());
        }
        else{
            return String.format("%d:%02d:%02d AM",hour,getTime().getMinute(),getTime().getSecond());
        }
    }
    }
}
