import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Scanner;

public class PAT1002 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        double[] pol = new double[1001];

        for (int i = 0; i < 2; i++) {
            int k = scanner.nextInt();
            for (int j = 0; j < k; j++) {
                int n = scanner.nextInt();
                double a = scanner.nextDouble();
                pol[n] += a;
            }
        }

        DecimalFormat format = new DecimalFormat("0.0");

        int count = 0;

        StringBuilder result = new StringBuilder();

        for (int i = pol.length - 1; i >= 0; i--) {
            if (pol[i] == 0) {
                continue;
            }
            count++;
            result.append(" ").append(i).append(" ").append(format.format(pol[i]));
        }

        result.insert(0, count);

        System.out.println(result.toString());
    }
}
