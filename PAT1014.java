import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class PAT1014 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        tokenizer.nextToken();
        int N = (int) tokenizer.nval;
        tokenizer.nextToken();
        int M = (int) tokenizer.nval;
        tokenizer.nextToken();
        int K = (int) tokenizer.nval;
        tokenizer.nextToken();
        int Q = (int) tokenizer.nval;

        int[] customers = new int[1010];
        int[] time = new int[1010];

        for (int i = 1; i <= K; i++) {
            tokenizer.nextToken();
            customers[i] = (int) tokenizer.nval;
        }

        int index = 1;
        PAT1014_Window[] windows = new PAT1014_Window[N];
        for (int i = 0; i < N; i++) {
            windows[i] = new PAT1014_Window();
            windows[i].queue.offer(index);
            windows[i].pop = customers[index];
            windows[i].end = customers[index];
            time[index] = windows[i].end;
            index++;
        }

        for (int i = 2; i <= M; i++) {
            for (int j = 0; j < N; j++) {
                if (index <= K) {
                    windows[j].queue.offer(index);
                    if (windows[j].end >= 540) {
                        time[index] = -1;
                    } else {
                        windows[j].end += customers[index];
                        time[index] = windows[j].end;
                    }
                    index++;
                }
            }
        }

        while (index <= K) {
            int min = 0;
            for (int i = 1; i < N; i++) {
                if (windows[min].pop > windows[i].pop) {
                    min = i;
                }
            }
            windows[min].queue.poll();
            windows[min].queue.offer(index);
            windows[min].pop += customers[windows[min].queue.peek()];
            if (windows[min].end >= 540) {
                time[index] = -1;
            } else {
                windows[min].end += customers[index];
                time[index] = windows[min].end;
            }
            index++;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            tokenizer.nextToken();
            int minute = time[(int) tokenizer.nval];
            if (minute != -1) {
                result.append(String.format("%02d:%02d\n", (minute + 480) / 60, (minute + 480) % 60));
            } else {
                result.append("Sorry\n");
            }
        }
        System.out.print(result);
    }
}

class PAT1014_Window {
    int pop;
    int end;
    Queue<Integer> queue = new LinkedList<>();
}
