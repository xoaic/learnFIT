package PR1Tutorial.Tut09;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Act4 {
    public static void main(String[] args) throws IOException {
        ArrayList<String> S = new ArrayList<String>();
        Scanner input = new Scanner(System.in);
        int n;
        int k;
        do {
            System.out.print("Enter N and K: ");
            n = input.nextInt();
            k = input.nextInt();
        } while (!(1 <= k) || !(k < n) || !(n <= 32));
        input.close();
        for (int i = 0; i < n; i++) {
            S.add("A");
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {}
        }
        try {
            FileWriter fileOut = new FileWriter("ketqua.txt");
            
            fileOut.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}