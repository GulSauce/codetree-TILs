import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Integer> firePos = new ArrayList<>();
        List<Integer> carPos = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            firePos.add(toInt(st));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            carPos.add(toInt(st));
        }

        new Solver(firePos, carPos).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    List<Integer> firePoses;
    List<Integer> carPoses;

    public Solver(List<Integer> firePoses, List<Integer> carPoses) {
        this.firePoses = firePoses;
        this.carPoses = carPoses;
    }

    public void solve() {
        Collections.sort(firePoses);
        Collections.sort(carPoses);
        // 불 인덱스
        int i = 0;
        // 소방차 인덱스
        int j = 0;
        int answer = getDist(i, j);
        for (; i < firePoses.size(); i++) {
            while (true) {
                if (j + 1 <= carPoses.size() - 1 && getDist(i, j) > getDist(i, j + 1)) {
                    j++;
                }
                answer = Math.max(answer, getDist(i, j));
                break;
            }
        }
        System.out.println(answer);
    }

    private int getDist(int fireIndex, int carIndex) {
        return Math.abs(firePoses.get(fireIndex) - carPoses.get(carIndex));
    }
}