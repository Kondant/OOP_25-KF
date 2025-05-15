import java.util.List;

public class Polygon {
    private List<Point> pointList;

    public boolean inside(Point point){
        int counter=0;
        double a,b,x;
        for(int i=0;i<pointList.size();i++) {
            Point pa = pointList.get(i);
            Point pb = pointList.get((i+1)%pointList.size());
            if (pa.b>pb.b){ //b=y
                Point temp = pa;
                pa = pb;
                pb = temp;
            }
            if(pa.b<point.b&& point.b<pb.b){
                double dy = pb.b - pa.b;
                double dx = pb.a - pa.a;
                double intersectionX;

                if (dy == 0) {
                    intersectionX = pa.a; // pozioma linia
                } else {
                    double slope = dx / dy;
                    intersectionX = pa.a + (point.b - pa.b) * slope;
                }

                if (intersectionX > point.a) {
                    counter++;
                }
            }
        }
        return (counter % 2 == 1);
        }
    public List<Point> getPoints() {
        return pointList;
    }
    public Polygon(List<Point> pointList) {
        this.pointList = pointList;
    }
}
