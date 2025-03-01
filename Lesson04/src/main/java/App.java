import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        Class<Student> studentClass = Student.class;
        System.out.println("###################### Student class fields");
        Stream.of(studentClass.getDeclaredFields()).forEach(field -> {
            System.out.println(field.getName());
        });

        System.out.println("###################### Student methods");
        Stream.of(studentClass.getDeclaredMethods()).forEach(field -> {
            System.out.println(field.getName());
        });
    }
}
