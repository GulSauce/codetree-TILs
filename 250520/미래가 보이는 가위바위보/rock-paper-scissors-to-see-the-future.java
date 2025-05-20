import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N;
        ArrayList<HSP> hsps = new ArrayList<>();
        hsps.add(HSP.valueOf("H"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            hsps.add(HSP.valueOf(st.nextToken()));
        }
        br.close();

        new Solver(hsps).solve();
    }
}

class Solver {

    ArrayList<HSP> hsps;

    int[][] leftPrefixSum;
    int[][] rightPrefixSum;

    public Solver(
        ArrayList<HSP> hsps
    ) {
        this.hsps = hsps;
        this.leftPrefixSum = new int[3][hsps.size()];
        this.rightPrefixSum = new int[3][hsps.size() + 1];
    }

    public void solve() {
        for (int i = 0; i < 3; i++) {
            for (int j = 1; j < hsps.size(); j++) {
                if (i == hsps.get(j).value) {
                    leftPrefixSum[i][j] = leftPrefixSum[i][j - 1] + 1;
                } else {
                    leftPrefixSum[i][j] = leftPrefixSum[i][j - 1];
                }
            }
            for (int j = hsps.size() - 1; j >= 1; j--) {
                if (i == hsps.get(j).value) {
                    rightPrefixSum[i][j] = rightPrefixSum[i][j + 1] + 1;
                } else {
                    rightPrefixSum[i][j] = rightPrefixSum[i][j + 1];
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < hsps.size(); i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j == k) {
                        continue;
                    }
                    answer = Math.max(answer,
                        leftPrefixSum[j][i] + rightPrefixSum[k][i + 1]);
                }
            }
        }
        System.out.println(answer);
    }
}

enum HSP {
    H(0),
    S(1),
    P(2);

    int value;

    HSP(
        int value
    ) {
        this.value = value;
    }
}