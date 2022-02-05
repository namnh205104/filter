import java.util.Scanner;

public class vong_xoay {
    public static void main(String[] args) {
        int[][] arr;
        int row, col;
        Scanner input = new Scanner(System.in);
        System.out.println("Input rows and columns: ");
        row = input.nextInt();
        col = input.nextInt();
        arr = new int[row][col];
        createSpiralMatrix(arr);
        showResult(arr);
        input.close();
    }

    private static void showResult(int[][] arr) {
        for (var row : arr) {
            for (var e : row) {
                System.out.printf("%5d", e);
            }
            System.out.println("\n");
        }
    }

    static void createSpiralMatrix(int a[][]) {
        int rowEnd = a.length;
        int colEnd = a[0].length;
        int rowStart = 0, colStart = 0;
        int value = 1;
        while (rowStart < rowEnd && colStart < colEnd) {
            for (int i = colStart; i < colEnd; ++i) {
                a[rowStart][i] = value++;
                if (value > 7) {
                    value = 1;
                }
            }
            rowStart++;
            for (int i = rowStart; i < rowEnd; ++i) {
                a[i][colEnd - 1] = value++;
                if (value > 7) {
                    value = 1;
                }
            }
            colEnd--;
            if (rowStart < rowEnd) {
                for (int i = colEnd - 1; i >= colStart; --i) {
                    a[rowEnd - 1][i] = value++;
                    if (value > 7) {
                        value = 1;
                    }
                }
                rowEnd--;
            }
            if (colStart < colEnd) {
                for (int i = rowEnd - 1; i >= rowStart; --i) {
                    a[i][colStart] = value++;
                    if (rowEnd + 1 == value) {
                        value = 1;
                    }
                }
                colStart++;
            }
        }
    }

}
