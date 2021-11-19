package PR1Tutorial.Tut09;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Act4 {
    static int m = 0;
    static String[] strArray;
    static int[] intArray;

    // Backtracking algorithm in my mine
    static String[] createArray(int l, int n) {
        for (int i = 0; i < 2; i++) {
            for (int j = l; j < n; j++) {
                intArray[j] = i;
                if (j == 4) {
                    m++;
                    strArray[m] = Arrays.toString(intArray);
                    break;
                }
                if (i == 1) {
                    l--;
                    return strArray;
                }
                createArray(j++, n);
            }
        }
        return strArray;
    }
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int n;
        int k;
        String check1 = "";
        ArrayList<String> strArray2 = new ArrayList<String>();
        do {
            System.out.print("Enter N and K: ");
            n = input.nextInt();
            k = input.nextInt();
        } while (!(1 <= k) || !(k < n) || !(n <= 32));
        for (int i = 0; i < k; i++) {
            check1 += "A";
        }
        String check2 = check1 + "A";
        intArray = new int[n];
        input.close();
        createArray(0, n);
        for (int i = 0; i < strArray.length; i++) {
            strArray[i].replaceAll("0", "A");
            strArray[i].replaceAll("1", "B");
            if (strArray[i].contains(check1)) {
                if (strArray[i].contains(check2)) {
                    break;
                }
                strArray2.add(strArray[i]);
            }
        }
        try {
            PrintWriter fileOut = new PrintWriter("ketqua.txt");
            fileOut.println(strArray2.size());
            for (int i = 0; i < strArray2.size(); i++) {
                fileOut.println(strArray2.get(i));
            }
            fileOut.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}