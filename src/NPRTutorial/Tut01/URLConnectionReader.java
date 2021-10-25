import java.net.*;
import java.io.*;
public class URLConnectionReader {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://hanu.vn");
        URLConnection uc = url.opneConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}