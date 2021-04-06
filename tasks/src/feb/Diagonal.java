package feb;

/*
Посчитать абсолютную разницу диагоналей в матрице n * n

пример:
1 2 3
4 5 6
9 8 9

1 + 5 + 9 = 15
3 + 5 + 9 = 17

|15 - 17| = 2
 */

public class Diagonal {
    public static void main(String[] args) {
        System.out.println(calculateDiagonalDiff(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                })
        );

        System.out.println(
                calculateDiagonalDiff(new int[][]{
                        {0, 2, 3},
                        {4, 0, 6},
                        {7, 8, 0}
                })
        );

        System.out.println(calculateDiagonalDiff(
                new int[][]{
                        {1, 2},
                        {3, 0}
                })
        );

        System.out.println(calculateDiagonalDiff(
                new int[][]{
                        {1,  2,  3,  6},
                        {5,  1,  6,  8},
                        {9,  6,  1, 12},
                        {6, 14, 15,  1}
                })
        );

        System.out.println(calculateDiagonalDiff(
                new int[][]{
                        {11, 2,  3,  4},
                        {5, 12,  3,  8},
                        {9,  2, 13, 12},
                        {1, 14, 15, 14}
                })
        );
    }

    private static long calculateDiagonalDiff(int[][] matrix) {
        int sumMainDiagonal = 0;
        int sumSecondaryDiagonal = 0;
        int mainDiagonalElem = 0;
        int secondaryDiagonalElem = matrix.length - 1;

        for (int[] array : matrix) {
            sumMainDiagonal += array[mainDiagonalElem];
            mainDiagonalElem++;

            sumSecondaryDiagonal += array[secondaryDiagonalElem];
            secondaryDiagonalElem--;
        }
        return Math.abs(sumMainDiagonal - sumSecondaryDiagonal);
    }
}
