import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class PAT1016 {
    static int month;
    static int[] rate = new int[25];

    static double calBill(PAT1016_Record record) {
        double result = record.day * 60 * rate[24] + rate[record.hour] * record.minute;
        for (int i = 0; i < record.hour; i++) {
            result += rate[i] * 60;
        }
        return result / 100;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] split = reader.readLine().split(" ");
        for (int i = 0; i < 24; i++) {
            rate[i] = Integer.parseInt(split[i]);
            rate[24] += rate[i];
        }

        int N = Integer.parseInt(reader.readLine());
        PAT1016_Record[] records = new PAT1016_Record[N];
        for (int i = 0; i < N; i++) {
            split = reader.readLine().split(" ");
            records[i] = new PAT1016_Record();
            records[i].name = split[0];
            String[] time = split[1].split(":");
            records[i].month = Integer.parseInt(time[0]);
            month = records[i].month;
            records[i].day = Integer.parseInt(time[1]);
            records[i].hour = Integer.parseInt(time[2]);
            records[i].minute = Integer.parseInt(time[3]);
            records[i].status = split[2].compareTo("on-line") == 0 ? 1 : 0;
            records[i].time = records[i].minute + records[i].hour * 60 + records[i].day * 60 * 24;
        }

        Arrays.sort(records);

        Map<String, ArrayList<PAT1016_Record>> map = new LinkedHashMap<>();

        for (int i = 1; i < N; i++) {
            if (records[i].name.compareTo(records[i - 1].name) == 0 && records[i - 1].status == 1 && records[i].status == 0) {
                ArrayList<PAT1016_Record> list = map.computeIfAbsent(records[i].name, k -> new ArrayList<>());
                list.add(records[i - 1]);
                list.add(records[i]);
            }
        }

        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, ArrayList<PAT1016_Record>> entry : map.entrySet()) {
            result.append(entry.getKey());
            ArrayList<PAT1016_Record> list = entry.getValue();
            result.append(String.format(" %02d\n", month));
            double total = 0;
            for (int i = 1; i < list.size(); i += 2) {
                PAT1016_Record on = list.get(i - 1);
                PAT1016_Record off = list.get(i);
                double cost = calBill(off) - calBill(on);
                total += cost;
                result.append(String.format("%02d:%02d:%02d %02d:%02d:%02d %d $%.2f\n", on.day, on.hour, on.minute,
                        off.day, off.hour, off.minute, off.time - on.time, cost));
            }
            result.append(String.format("Total amount: $%.2f\n", total));
        }
        System.out.print(result);
    }
}

class PAT1016_Record implements Comparable<PAT1016_Record> {
    String name;
    int status;
    int month;
    int day;
    int hour;
    int minute;
    int time;

    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", time=" + time +
                '}';
    }

    @Override
    public int compareTo(PAT1016_Record o) {
        if (name.compareTo(o.name) != 0) return name.compareTo(o.name);
        return time - o.time;
    }
}