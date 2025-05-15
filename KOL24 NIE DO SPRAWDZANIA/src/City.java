import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class City {
    private String name;
    private int Timezone;
    private String szer;
    private String dlug;

    public City(String name,int Timezone,String szer,String dlug) {
        this.name= name;
        this.Timezone=Timezone;
        this.szer=szer;
        this.dlug = dlug;
    }
    private static City parseLine(String line){
        String[] parts= line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Zły format linii");
        }
        String name=parts[0];
        int Timezone=Integer.parseInt(parts[1]);
        String szer=parts[2];
        String dlug = parts[3];
        City city=new City(name,Timezone,szer,dlug);
        return city;
    }
    public static Map<String, City> parseFile(String filePath) {
        Map<String,City> cities = new HashMap<>();
        try{
        File file =new File(filePath);
        Scanner in=new Scanner(file);
        if(in.hasNextLine()){
            in.nextLine();
        }
        while(in.hasNextLine()){
            String line = in.nextLine();
            City city = parseLine(line);
            cities.put(city.getName(),city);
        }
    }catch (FileNotFoundException e){
            e.printStackTrace();
    }
        return cities;
    }
    private double zmianaDlug(String dlug) {
        String[] parts=dlug.trim().split(" ");
        double wspol = Double.parseDouble(parts[0]);
        String kierunek=parts[1];
        if(kierunek.equalsIgnoreCase("W")){
            wspol=-wspol;
        }
        return wspol;
    }
    private double zmianaSzer(String szer) {
        String[] parts = szer.trim().split(" ");
        double wspol = Double.parseDouble(parts[0]);
        String kierunek=parts[1];
        if(kierunek.equalsIgnoreCase("S")) {
            wspol=-wspol;
        }
        return wspol;
    }
    public LocalTime localMeanTime( LocalTime time) {
        double dlugValue = zmianaDlug(this.dlug);
        double przesGodz = (dlugValue / 180.0) * 12;
        int sekundyDoDod = (int) (przesGodz * 60 * 60);
        return time.plusSeconds(sekundyDoDod).withNano(0);
    }
    public static int worstTimezoneFit(City city1, City city2) {
    int timeDiff1 = Math.abs(city1.getTimezone()- city1.localMeanTime(LocalTime.of(12,0)).getHour());
    int timeDiff2 = Math.abs(city2.getTimezone()- city2.localMeanTime(LocalTime.of(12,0)).getHour());
       return Integer.compare(timeDiff2,timeDiff1);
        }
public static void generateAnalogClocksSvg(List<City> cities, AnalogClock clock) {
        String directoryName = clock.toString();
        File directory = new File(directoryName);
    if (!directory.exists()) {
        directory.mkdirs();
    }
    for(City city : cities){
        clock.setCity(city);
        String fileName=city.getName() + ".svg";
        File svgFile=new File(directory,fileName);
        clock.toSvg(svgFile.getAbsolutePath());
        }
    }

    public String getDlug() {
        return dlug;
    }

    public String getName() {
        return name;
    }

    public String getSzer() {
        return szer;
    }

    public int getTimezone() {
        return Timezone;
    }
}
