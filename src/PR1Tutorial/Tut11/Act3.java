package PR1Tutorial.Tut11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

class Act3 {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<Integer, String> list = new HashMap<Integer, String>();
        String[] words = new String[0];
        File file = new File("English.txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String sentence = input.nextLine();
            words = sentence.split(" ");
        }
    }
}