package Tut04;

import java.util.Scanner;

public class Exe1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the nums of list: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        int[] A = new int[n];
        int[] B = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter the integer " + (i+1) + ": ");
            A[i] = scanner.nextInt();
        }

        scanner.close();

        int x = 0, y = n-1;

        for (int i = 0; i < n; i++) {
            if (A[i] < 0) {
                B[x++] = A[i];
            } else if (A[i] > 0) {
                B[y--] = A[i];
            }
        }

        System.out.print("The re-arrange is: ");

        for (int i = 0; i < n; i++) {
            System.out.print(B[i] + " ");
        }
    }
}
