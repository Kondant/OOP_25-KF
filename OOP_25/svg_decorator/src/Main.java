import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Polygon triangle = new Polygon(new Vec2[]{
                new Vec2(0, 0),
                new Vec2(300, 0),
                new Vec2(150, 250)
        });

        Polygon rectangle = new Polygon(new Vec2[]{
                new Vec2(350, 0),
                new Vec2(750, 0),
                new Vec2(750, 200),
                new Vec2(350, 200)
        });

        Polygon pentagon = new Polygon(new Vec2[]{
                new Vec2(0, 260),
                new Vec2(100, 460),
                new Vec2(300, 560),
                new Vec2(500, 460),
                new Vec2(600, 260)
        });
        SolidFillShapeDecorator redPentagon=new SolidFillShapeDecorator(pentagon,"red");
        TransformationDecorator transforRedPentagon=new TransformationDecorator.Builder()
                .translate((new Vec2(50,50)))
                .rotate(180, new Vec2(10,10))
                .scale(new Vec2(0.5,1))
                .build(redPentagon);
        Shape ellipse = new Ellipse(new Vec2(500, 700), 400, 100);
        ellipse=new SolidFillShapeDecorator(ellipse,"green");
        ellipse = new StrokeShapeDecorator(ellipse,"pink",5.0);
        SvgScene scene = new SvgScene();
        scene.addShape(triangle);
        scene.addShape(rectangle);
        scene.addShape(transforRedPentagon);
        scene.addShape(ellipse);
        scene.save("result.svg");
    }
}
