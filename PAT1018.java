import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class PAT1018 {
    static ArrayList<Integer> tmp = new ArrayList<>();
    static ArrayList<Integer> result;
    static ArrayList<Integer>[] paths;
    static int[] bikes;
    static int minNeed = Integer.MAX_VALUE;
    static int minBack = Integer.MAX_VALUE;

    static void dfs(int index) {
        tmp.add(index);
        if (index == 0) {
            int need = 0, back = 0;
            for (int i = tmp.size() - 1; i >= 0; i--) {
                int now = tmp.get(i);
                if (bikes[now] > 0) {
                    back += bikes[now];
                } else {
                    if (back > Math.abs(bikes[now])) {
                        back += bikes[now];
                    } else {
                        need += Math.abs(bikes[now]) - back;
                        back = 0;
                    }
                }
            }
            if (need < minNeed) {
                minNeed = need;
                minBack = back;
                result = new ArrayList<>(tmp);
            } else if (need == minNeed && back < minBack) {
                minBack = back;
                result = new ArrayList<>(tmp);
            }
            tmp.remove(tmp.size() - 1);
            return;
        }
        for (int i = 0; i < paths[index].size(); i++) {
            dfs(paths[index].get(i));
        }
        tmp.remove(tmp.size() - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] split = reader.readLine().split(" ");
        int C = Integer.parseInt(split[0]);
        int N = Integer.parseInt(split[1]);
        int S = Integer.parseInt(split[2]);
        int M = Integer.parseInt(split[3]);

        int[][] roads = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(roads[i], Integer.MAX_VALUE);
        }
        bikes = new int[N + 1];

        split = reader.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            int bike = Integer.parseInt(split[i - 1]);
            bike -= C / 2;
            bikes[i] = bike;
        }

        for (int i = 0; i < M; i++) {
            split = reader.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);
            int dis = Integer.parseInt(split[2]);
            roads[from][to] = roads[to][from] = dis;
        }

        paths = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            paths[i] = new ArrayList<>();
        }

        int[] dis = new int[N + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[0] = 0;

        boolean[] vis = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            int now = -1, min = Integer.MAX_VALUE;
            for (int j = 0; j <= N; j++) {
                if (!vis[j] && dis[j] < min) {
                    now = j;
                    min = dis[j];
                }
            }
            if (now == -1) {
                break;
            }
            vis[now] = true;
            for (int j = 0; j <= N; j++) {
                if (!vis[j] && roads[now][j] != Integer.MAX_VALUE) {
                    if (dis[j] > dis[now] + roads[now][j]) {
                        dis[j] = dis[now] + roads[now][j];
                        paths[j].clear();
                        paths[j].add(now);
                    } else if (dis[j] == dis[now] + roads[now][j]) {
                        paths[j].add(now);
                    }
                }
            }
        }

        dfs(S);

        StringBuilder out = new StringBuilder();
        out.append(minNeed).append(" 0");
        for (int i = result.size() - 2; i >= 0; i--) {
            out.append("->").append(result.get(i));
        }
        out.append(" ").append(minBack);
        System.out.println(out);
    }
}
