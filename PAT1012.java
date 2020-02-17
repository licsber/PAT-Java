import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PAT1012 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] out = {"A", "C", "M", "E"};
        PAT1012_Stu[] sid = new PAT1012_Stu[1000000];
        ArrayList<PAT1012_Stu> ss = new ArrayList<>();

        String[] split = reader.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);


        for (int i = 0; i < N; i++) {
            split = reader.readLine().split(" ");
            int index = Integer.parseInt(split[0]);
            sid[index] = new PAT1012_Stu(split[1], split[2], split[3]);
            ss.add(sid[index]);
        }


        for (int i = 0; i < 4; i++) {
            final int finalI = i;
            // dont change this !
            Collections.sort(ss, new Comparator<PAT1012_Stu>() {
                @Override
                public int compare(PAT1012_Stu o1, PAT1012_Stu o2) {
                    return o2.grade[finalI] - o1.grade[finalI];
                }
            });

            ss.get(0).rank[i] = 1;
            for (int j = 1; j < N; j++) {
                PAT1012_Stu last = ss.get(j - 1);
                PAT1012_Stu now = ss.get(j);
                if (now.grade[i] == last.grade[i]) {
                    now.rank[i] = last.rank[i];
                } else {
                    now.rank[i] = j + 1;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int query = Integer.parseInt(reader.readLine());
            PAT1012_Stu stu = sid[query];
            if (stu == null) {
                result.append("N/A\n");
            } else {
                int index = 0;
                for (int j = 1; j < 4; j++) {
                    if (stu.rank[index] > stu.rank[j]) {
                        index = j;
                    }
                }
                result.append(stu.rank[index]).append(" ").append(out[index]).append("\n");
            }
        }
        System.out.print(result);
    }
}

class PAT1012_Stu {
    int[] grade = new int[4];
    int[] rank = new int[4];

    public PAT1012_Stu(String c, String m, String e) {
        grade[1] = Integer.parseInt(c);
        grade[2] = Integer.parseInt(m);
        grade[3] = Integer.parseInt(e);
        grade[0] = grade[1] + grade[2] + grade[3];
    }
}
