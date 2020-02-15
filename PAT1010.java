import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PAT1010 {
    private static long parse(String s, long radix) {
        long result = 0;

        int len = s.length();
        for (int i = len - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            int value = Character.isDigit(ch) ? ch - '0' : ch - 'a' + 10;
            result += (long) (value * Math.pow(radix, len - i - 1));
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();

        String[] split = line.split(" ");

        long N1, N2;
        String remain;

        int tag = Integer.parseInt(split[2]);
        int radix = Integer.parseInt(split[3]);
        if (tag == 1) {
            N1 = parse(split[0], radix);
            remain = split[1];
        } else {
            N1 = parse(split[1], radix);
            remain = split[0];
        }

        char[] chars = remain.toCharArray();
        Arrays.sort(chars);
        char ch = chars[chars.length - 1];

        long left = Character.isDigit(ch) ? ch - '0' : ch - 'a' + 10;
        left++;
        long right = Math.max(N1, left);
        while (left <= right) {
            long mid = (left + right) / 2;
            N2 = parse(remain, mid);
            if (N1 == N2) {
                System.out.println(mid);
                System.exit(0);
            }
            if (N2 < 0 || N1 < N2) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println("Impossible");
    }
}
