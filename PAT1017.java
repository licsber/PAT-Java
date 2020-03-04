import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class PAT1017 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] split = reader.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        ArrayList<Cos> cos = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            split = reader.readLine().split(" ");
            String[] comes = split[0].split(":");
            int hour = Integer.parseInt(comes[0]);
            int min = Integer.parseInt(comes[1]);
            int sec = Integer.parseInt(comes[2]);
            int come = sec + min * 60 + hour * 3600;
            if (come > 17 * 3600) {
                continue;
            }
            int cost = Integer.parseInt(split[1]) * 60;
            cos.add(new Cos(come, cost));
        }

        if (cos.size() == 0) {
            System.out.println("0.0");
            return;
        }

        cos.sort(new Comparator<Cos>() {
            @Override
            public int compare(Cos cos, Cos t1) {
                return cos.come - t1.come;
            }
        });

        int[] win = new int[K];
        Arrays.fill(win, 8 * 3600);

        int result = 0;
        for (Cos co : cos) {
            int min = 0;
            int value = win[0];
            for (int j = 1; j < K; j++) {
                if (win[j] < value) {
                    min = j;
                    value = win[j];
                }
            }
            if (win[min] <= co.come) {
                win[min] = co.come + co.time;
            } else {
                result += win[min] - co.come;
                win[min] += co.time;
            }
        }
        DecimalFormat format = new DecimalFormat("0.0");

        System.out.println(format.format(result / 60.0 / cos.size()));
    }
}

class Cos {
    int come;
    int time;

    public Cos(int come, int time) {
        this.come = come;
        this.time = time;
    }
}