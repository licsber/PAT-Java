import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1007 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        reader.readLine();

        String[] split = reader.readLine().split(" ");

        int now = 0, max = Integer.MIN_VALUE, tmp = 0, from = 0, to = split.length - 1;
        for (int i = 0; i < split.length; i++) {
            int next = Integer.parseInt(split[i]);
            now += next;
            if (now < 0) {
                now = 0;
                tmp = i + 1;
            } else if (now > max) {
                max = now;
                from = tmp;
                to = i;
            }
        }

        if (max < 0) {
            max = 0;
        }

        System.out.printf("%d %s %s", max, split[from], split[to]);
    }
}
