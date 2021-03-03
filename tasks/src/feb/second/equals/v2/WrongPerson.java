package feb.second.equals.v2;

import java.util.Objects;

public class WrongPerson extends Person {
    int age;

    public WrongPerson(String name, int age) {
        super(name);
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!super.equals(o)) return false;
        WrongPerson that = (WrongPerson) o;
        return age == that.age && WrongPerson.class == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), age);
    }
}
