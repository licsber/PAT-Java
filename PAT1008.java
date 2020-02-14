import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1008 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        line = line.substring(line.indexOf(" ") + 1);

        String[] split = line.split(" ");

        long result = 0;
        int now = 0;

        for (String s : split) {
            int value = Integer.parseInt(s);

            int diff = now - value;

            if (diff < 0) {
                diff = Math.abs(diff);
                result += diff * 6;
            } else {
                result += diff * 4;
            }

            now = value;
            result += 5;
        }

        System.out.println(result);
    }
}
