import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Person> personList = Person.fromCsv("family.csv");
            System.out.println("family.csv");
            for (Person p: personList){
                System.out.println(p);
                for (Person child : p.getChildren()) {
                    System.out.println("\t"+child.getFullName());
                }
            }
        } catch (AmbiguousPersonException e) {
            System.err.println(e.getMessage());
        }


    }
}