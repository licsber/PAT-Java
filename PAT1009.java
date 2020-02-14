import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class PAT1009 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        line = line.substring(line.indexOf(" ") + 1);

        double[] pol = new double[2020];

        String[] split = line.split(" ");
        for (int i = 0; i < split.length; i += 2) {
            int n = Integer.parseInt(split[i]);
            double a = Double.parseDouble(split[i + 1]);
            pol[n] = a;
        }

        line = reader.readLine();
        line = line.substring(line.indexOf(" ") + 1);

        double[] mul = new double[2020];

        split = line.split(" ");
        for (int i = 0; i < split.length; i += 2) {
            int n = Integer.parseInt(split[i]);
            double a = Double.parseDouble(split[i + 1]);
            for (int j = pol.length - 1; j >= 0; j--) {
                if (pol[j] == 0) {
                    continue;
                }
                mul[n + j] += pol[j] * a;
            }
        }

        StringBuilder result = new StringBuilder();
        int count = 0;

        DecimalFormat format = new DecimalFormat("0.0");

        for (int i = mul.length - 1; i >= 0; i--) {
            if (mul[i] == 0) {
                continue;
            }
            count++;
            result.append(" ").append(i).append(" ").append(format.format(mul[i]));
        }
        System.out.print(count);
        System.out.println(result);
    }
}
