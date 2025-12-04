import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = toInt(st);
            int end = toInt(st);
            schedules.add(new Schedule(start, end));
        }
        new Solver(schedules).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<Schedule> schedules;

    public Solver(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void solve() {
        Collections.sort(schedules);
        int answer = 1;
        int curFinish = schedules.get(0).end;
        for (int i = 1; i < schedules.size(); i++) {
            if (schedules.get(i).start < curFinish) {
                continue;
            }
            curFinish = schedules.get(i).end;
            answer++;
        }
        System.out.println(answer);
    }
}

class Schedule implements Comparable<Schedule> {

    int start;
    int end;

    public Schedule(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Schedule o) {
        return Integer.compare(this.end, o.end);
    }
}