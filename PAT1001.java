import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Scanner;

public class PAT1001 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int sum = a + b;

        NumberFormat format = NumberFormat.getInstance();

        String result = format.format(sum);
        System.out.println(result);
    }
}
