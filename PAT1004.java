import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PAT1004 {
    static ArrayList<Integer>[] tree = new ArrayList[128];
    static int[] ans = new int[128];
    static int max_depth = -1;

    static void dfs(int index, int depth) {
        if (tree[index].size() == 0) {
            ans[depth]++;
            max_depth = Math.max(max_depth, depth);
        }
        for (int child : tree[index]) {
            dfs(child, depth + 1);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int N = scanner.nextInt();
        if (N == 0) {
            System.out.println(N);
            System.exit(0);
        }

        int M = scanner.nextInt();
        for (int i = 0; i < M; i++) {
            int root = scanner.nextInt();
            int count = scanner.nextInt();
            for (int j = 0; j < count; j++) {
                int child = scanner.nextInt();
                tree[root].add(child);
            }
        }

        dfs(1, 0);

        System.out.print(ans[0]);
        for (int i = 1; i <= max_depth; i++) {
            System.out.print(" " + ans[i]);
        }
    }
}
