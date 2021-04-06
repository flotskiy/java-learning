package feb;

/*
Поместить строку в центр другой строки
пример
s1 = "abceee";
s2 = "vvvbbb";
resulted sting is "abcvvvbbbeee";
 */

public class StringsConnector {
    public static void main(String[] args) {
        String first = "This is the first string!!!";
        String second = "--hahahaha--";

        insertStringIntoAnotherString(first, second);
    }

    private static void insertStringIntoAnotherString(String first, String second) {
        System.out.println("Input strings are:");
        System.out.println(first);
        System.out.println(second);
        System.out.println();

        System.out.println("Result:");
        int insertPosition = first.length() / 2;
        System.out.println(first.substring(0, insertPosition) + second + first.substring(insertPosition));
    }
}
