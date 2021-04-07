package equals.v1;

import java.util.Objects;

public class Man extends Person {
    public Man(String name, int age, int height) {
        super(name, age, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() == o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && height == person.height && Objects.equals(name, person.name);
    }
}
