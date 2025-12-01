import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;
        List<Integer> livePos = new ArrayList<>();
        List<Integer> velocities = new ArrayList<>();
        List<PersonInfo> personInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            livePos.add(toInt(st));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            velocities.add(toInt(st));
        }

        for (int i = 0; i < N; i++) {
            personInfos.add(new PersonInfo(livePos.get(i), velocities.get(i)));
        }

        new Solver(personInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<PersonInfo> personInfos;
    private int minPos;

    public Solver(List<PersonInfo> personInfos) {
        this.personInfos = personInfos;
    }

    public void solve() {
        double left = 0;
        double right = 1_000_000_000;
        double answer = right;
        for (int time = 0; time < 100; time++) {
            double mid = (left + right) / 2;
            if (isValid(mid)) {
                right = mid;
                answer = Math.min(answer, mid);
            } else {
                left = mid;
            }
        }
        System.out.printf("%.4f", answer);
    }

    private boolean isValid(double maxTime) {
        double left = personInfos.get(0).livePos - personInfos.get(0).velocity * maxTime;
        double right = personInfos.get(0).livePos + personInfos.get(0).velocity * maxTime;
        for (int i = 1; i < personInfos.size(); i++) {
            PersonInfo personInfo = personInfos.get(i);
            double curLeft = personInfo.livePos - personInfo.velocity * maxTime;
            double curRight = personInfo.livePos + personInfo.velocity * maxTime;
            if (curRight < left) {
                return false;
            }
            if (right < curLeft) {
                return false;
            }
            left = Math.max(left, curLeft);
            right = Math.min(right, curRight);
        }
        return true;
    }
}

class PersonInfo {

    int livePos;
    int velocity;

    public PersonInfo(int livePos, int velocity) {
        this.livePos = livePos;
        this.velocity = velocity;
    }
}