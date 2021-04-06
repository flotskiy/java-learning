package feb;

/*
Найти кол-во вхождений наибольшего числа в массиве
пример -
[4,2,3,1,4] -> 2
 */

public class EntryCounter {
    public static void main(String[] args) {
        int[] array1 = new int[] {4, 2, 3, 5, 3, 5};
        int[] array2 = new int[] {-1, -1, -1, -7, -4, -1};
        int[] array3 = new int[] {-1, 1, 2, 3, 4, 5, 1, 2};

        System.out.println(countMaxNumbers(array1));
        System.out.println(countMaxNumbers(array2));
        System.out.println(countMaxNumbers(array3));
        System.out.println();
    }

    public static int countMaxNumbers(int[] array) {
        printArray(array);
        int count = 1;
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                count = 1;
                continue;
            }

            if (array[i] == max) {
                count++;
            }
        }
        return count;
    }

    private static void printArray(int[] array) {
        for (int number : array) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
