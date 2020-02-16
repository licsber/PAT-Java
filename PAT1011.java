import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class PAT1011 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double[][] input = new double[3][3];

        for (int i = 0; i < 3; i++) {
            String line = reader.readLine();
            String[] split = line.split(" ");
            for (int j = 0; j < 3; j++) {
                input[i][j] = Double.parseDouble(split[j]);
            }
        }

        String[] buy = {"W", "T", "L"};

        double result = 1;
        for (int i = 0; i < 3; i++) {
            int index = 0;
            double max = -1;
            for (int j = 0; j < 3; j++) {
                max = Math.max(max, input[i][j]);
                if (max == input[i][j]) {
                    index = j;
                }
            }
            System.out.print(buy[index] + " ");
            result *= max;
        }

        result *= 0.65;
        result -= 1;
        result *= 2;

        DecimalFormat format = new DecimalFormat("0.00");
        System.out.println(format.format(result));
    }
}
