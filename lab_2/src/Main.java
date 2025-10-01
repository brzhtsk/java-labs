import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Person oldPerson = new Person("Bryzhitska", "Diana", 19);
        Gson converter = new GsonBuilder().create();
        String serializedPerson = converter.toJson(oldPerson);
        Person newPerson = converter.fromJson(serializedPerson, Person.class);
        System.out.println(oldPerson == newPerson);
        System.out.println(oldPerson.equals(newPerson));
    }
}