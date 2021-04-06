package feb.equals.v2;

public class PersonTest {
    public static void main(String[] args) {
        Person person = new Person("Vitya");
        WrongPerson wrongPerson = new WrongPerson("Vitya", 15);

        System.out.println(person.equals(wrongPerson));
        System.out.println(wrongPerson.equals(person));
    }
}
