public class NegativeLifespanException extends RuntimeException{
    public NegativeLifespanException(Person p){
        super(p+ "Negative Lifespan");
    }
}
