import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PAT1005 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String input = scanner.nextLine();

        int sum = 0;
        int len = input.length();
        for (int i = 0; i < len; i++) {
            sum += input.charAt(i) - '0';
        }

        String[] words = {"zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine"};

        String output = Integer.toString(sum);

        len = output.length();
        System.out.print(words[output.charAt(0) - '0']);
        for (int i = 1; i < len; i++) {
            System.out.print(" " + words[output.charAt(i) - '0']);
        }
    }
}
