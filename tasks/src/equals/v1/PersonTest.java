/*
Создать свой класс и переопределить equals таким образом,
чтобы сво-во симметри или транзитивности не выполнялось
 */

package equals.v1;

public class PersonTest {
    public static void main(String[] args) {
        Person vasiliy1 = new Man("Vasiliy", 20, 170);
        Person vasiliy2 = new Man("Vasiliy", 20, 170);

        Person vasiliy3 = new Woman("Vasiliy", 20, 170);

        System.out.println("Сравнение двух разных Василиев с одинаковыми значениями полей, но из разных классов:");
        System.out.println(vasiliy1.equals(vasiliy3));
        System.out.println(vasiliy3.equals(vasiliy1));
        System.out.println();

        System.out.println("Сравнение трёх разных Василиев с одинаковыми значениями полей, но из разных классов:");
        System.out.println(vasiliy1.equals(vasiliy2));
        System.out.println(vasiliy1.equals(vasiliy3));
        System.out.println(vasiliy3.equals(vasiliy1));
        System.out.println(vasiliy2.equals(vasiliy3));
    }
}
