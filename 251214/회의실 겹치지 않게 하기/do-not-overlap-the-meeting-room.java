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
        List<OfficeInfo> officeInfos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = toInt(st);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = toInt(st);
            int end = toInt(st);
            officeInfos.add(new OfficeInfo(start, end));
        }

        new Solver(officeInfos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<OfficeInfo> officeInfos;

    public Solver(List<OfficeInfo> officeInfos) {
        this.officeInfos = officeInfos;
    }

    public void solve() {
        Collections.sort(officeInfos, (a, b) -> Integer.compare(a.end, b.end));
        int prevEnd = 0;
        int answer = 0;
        for (OfficeInfo officeInfo : officeInfos) {
            if (officeInfo.start < prevEnd) {
                answer++;
            } else {
                prevEnd = officeInfo.end;
            }
        }
        System.out.println(answer);
    }
}

class OfficeInfo {

    int start;
    int end;

    public OfficeInfo(int start, int end) {
        this.start = start;
        this.end = end;
    }
}