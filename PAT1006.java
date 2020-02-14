import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Scanner;

public class PAT1006 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int M = scanner.nextInt();

        String unlock = null, lock = null;

        Calendar max = Calendar.getInstance();
        max.set(Calendar.HOUR_OF_DAY, 0);
        max.set(Calendar.MINUTE, 0);
        max.set(Calendar.SECOND, 0);

        Calendar min = Calendar.getInstance();
        min.set(Calendar.HOUR_OF_DAY, 23);
        min.set(Calendar.MINUTE, 59);
        min.set(Calendar.SECOND, 59);

        for (int i = 0; i < M; i++) {
            String name = scanner.next();

            String sign_in = scanner.next();
            String[] in = sign_in.split(":");
            Calendar tmp = Calendar.getInstance();
            tmp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(in[0]));
            tmp.set(Calendar.MINUTE, Integer.parseInt(in[1]));
            tmp.set(Calendar.SECOND, Integer.parseInt(in[2]));
            if (min.compareTo(tmp) >= 0) {
                unlock = name;
                min = tmp;
            }

            String sign_out = scanner.next();
            String[] out = sign_out.split(":");
            tmp = Calendar.getInstance();
            tmp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(out[0]));
            tmp.set(Calendar.MINUTE, Integer.parseInt(out[1]));
            tmp.set(Calendar.SECOND, Integer.parseInt(out[2]));
            if (max.compareTo(tmp) <= 0) {
                lock = name;
                max = tmp;
            }
        }
        System.out.printf("%s %s", unlock, lock);
    }
}
