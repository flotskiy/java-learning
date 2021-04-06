package feb.equals.v2;

import java.util.Objects;

public class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Person person = (Person) o;
        return name.equals(person.name) && WrongPerson.class == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
