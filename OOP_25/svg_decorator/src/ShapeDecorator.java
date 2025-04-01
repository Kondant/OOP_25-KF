public class ShapeDecorator implements Shape{
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }
    @Override
    public BoundingBox boundingBox(){
        if(decoratedShape==null)
            return null;
        return decoratedShape.boundingBox();
    }
    @Override
    public String toSvg(String inputSvg){
        if(decoratedShape==null)
            return "";
        return decoratedShape.toSvg(inputSvg);
    }
}
