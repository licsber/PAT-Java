import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class PAT1013 {
    static boolean[] vis;
    static int[][] cities;

    static void dfs(int index) {
        vis[index] = true;
        for (int i = 1; i < cities.length; i++) {
            if (!vis[i] && cities[index][i] == 1) {
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        
        tokenizer.nextToken();
        int N = (int) tokenizer.nval;
        tokenizer.nextToken();
        int M = (int) tokenizer.nval;
        tokenizer.nextToken();
        int K = (int) tokenizer.nval;

        cities = new int[N + 1][N + 1];
        vis = new boolean[N + 1];
        vis[0] = true;

        for (int i = 0; i < M; i++) {
            tokenizer.nextToken();
            int from = (int) tokenizer.nval;
            tokenizer.nextToken();
            int to = (int) tokenizer.nval;
            cities[from][to] = cities[to][from] = 1;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < K; i++) {
            tokenizer.nextToken();
            int index = (int) tokenizer.nval;
            vis[index] = true;
            int count = -1;
            for (int j = 1; j < cities.length; j++) {
                if (!vis[j]) {
                    dfs(j);
                    count++;
                }
            }
            result.append(count).append("\n");
            Arrays.fill(vis, false);
        }
        System.out.print(result);
    }
}
