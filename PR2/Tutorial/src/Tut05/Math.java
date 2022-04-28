package Tut05;

import java.util.Scanner;

public class Exe4 {
    public static int remainder(int A, int B) {
        while (A > B) A -= B;
        return A;
    }

    public static int div(int A, int B) {
        int C = 0;
        while (A > B) {
            A -= B;
            C++;
        }
        return C;
    }

    public static int middle(int A, int B, int C) {
        if (A > B) {
            if (A < C) return A;
            if (B > C) return B;
            else return C;
        } else {
            if (A > C) return A;
            if (B > C) return C;
            else return B;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Math Menu: \n" +
                "[1] remainder\n" +
                "[2] div\n" +
                "[3] middle\n" +
                "Enter: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Enter divisor and division: ");
                int A = scanner.nextInt();
                int B = scanner.nextInt();
                if (B != 0) System.out.print("The remainder of " + A + " and " + B + " is : " + remainder(A, B));
                break;
            case 2:
                System.out.print("Enter divisor and division: ");
                int A = scanner.nextInt();
                int B = scanner.nextInt();
                if (B != 0) System.out.print("The remainder of " + A + " and " + B + " is : " + remainder(A, B));
                break;
            case 3:
                System.out.print("Enter divisor and division: ");
                int A = scanner.nextInt();
                int B = scanner.nextInt();
                if (B != 0) System.out.print("The remainder of " + A + " and " + B + " is : " + remainder(A, B));
                break;
            default:
                System.out.print("Invalid choice!");
        scanner.close();
        }
    }
}
