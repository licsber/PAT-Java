import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class PAT1003 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int start = scanner.nextInt();
        int end = scanner.nextInt();

        int[] vertex_team = new int[N];
        for (int i = 0; i < N; i++) {
            vertex_team[i] = scanner.nextInt();
        }

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < M; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int dis = scanner.nextInt();
            map[from][to] = map[to][from] = dis;
        }

        int[] road_count = new int[N];
        road_count[start] = 1;

        int[] people = new int[N];
        people[start] = vertex_team[start];

        int[] dis = new int[N];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;

        int[] pre = new int[N];
        Arrays.fill(pre, -1);
        pre[start] = start;

        boolean[] vis = new boolean[N];

        for (int i = 0; i < N; i++) {
            int now = -1, min = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (!vis[j] && dis[j] < min) {
                    now = j;
                    min = dis[j];
                }
            }
            if (now == -1) {
                break;
            }
            vis[now] = true;
            for (int j = 0; j < N; j++) {
                if (!vis[j] && map[now][j] != Integer.MAX_VALUE) {
                    if (dis[now] + map[now][j] < dis[j]) {
                        dis[j] = dis[now] + map[now][j];
                        pre[j] = pre[now];
                        people[j] = people[now] + vertex_team[j];
                        road_count[j] = road_count[now];
                    } else if (dis[now] + map[now][j] == dis[j]) {
                        road_count[j] += road_count[now];
                        if (people[now] + vertex_team[j] > people[j]) {
                            people[j] = people[now] + vertex_team[j];
                        }
                    }
                }
            }
        }
        System.out.printf("%d %d", road_count[end], people[end]);
    }
}
