import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class PAT1015 {
    public static String reverse(int num, int radix) {
        StringBuilder result = new StringBuilder();
        while (num != 0) {
            result.append(num % radix);
            num /= radix;
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        boolean[] prime = new boolean[60000];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;

        for (int i = 2; i < prime.length; i++) {
            if (prime[i]) {
                for (int j = 2 * i; j < prime.length; j += i) {
                    prime[j] = false;
                }
            }
        }

        StringBuilder result = new StringBuilder();

        while (true) {
            tokenizer.nextToken();
            int N = (int) tokenizer.nval;
            if (N < 0) {
                break;
            }
            tokenizer.nextToken();
            int D = (int) tokenizer.nval;
            if (prime[N]) {
                int num = Integer.parseInt(reverse(N, D), D);
                if (prime[num]) {
                    result.append("Yes\n");
                    continue;
                }
            }
            result.append("No\n");
        }

        System.out.print(result);
    }
}
