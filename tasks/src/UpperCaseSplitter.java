/*
Есть строка в camelCase, надо вывести все подстроки, начинающиеся на загланую букву
пример:
ILoveJavaProgramming
result:
I
Love
Java
Programming
*/

import java.util.ArrayList;
import java.util.List;

public class UpperCaseSplitter {
    public static void main(String[] args) {
        printArray(splitByUpperCase("ILoveJavaProgramming"));
        System.out.println();
        printArray(splitByUpperCase("aaaaILoveJavaProgramming"));
        System.out.println();
        printArray(splitByUpperCase("абвГдЕЁжЗийКлмн"));
    }

    private static List<String> splitByUpperCase(String input) {
        String[] temp = input.split("(?=\\p{Lu})");
        List<String> list = new ArrayList<>();
        for (String string : temp) {
            if (string.matches("[A-ZА-ЯЁ].*")) {
                list.add(string);
            }
        }
        return list;
    }

    private static void printArray(List<String> list) {
        list.forEach(System.out::println);
    }
}
