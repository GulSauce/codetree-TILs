import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st);
        M = toInt(st);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(toInt(st));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B.add(toInt(st));
        }

        new Solver(A, B).solve();
    }

    private static int toInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}

class Solver {

    final int MAX_LENGTH = 100_000;
    final int NOT_ALLOCATED = -1;
    List<Integer> mainArray;
    List<Integer> subArray;
    int[] L = new int[MAX_LENGTH + 1];
    int[] R = new int[MAX_LENGTH + 1];

    public Solver(List<Integer> A, List<Integer> B) {
        this.mainArray = A;
        this.subArray = B;
    }

    private void calcL() {
        Arrays.fill(L, NOT_ALLOCATED);
        int i = 0;
        int j = 0;
        for (; i < subArray.size(); i++) {
            while (true) {
                if (j == mainArray.size()) {
                    break;
                }
                if (!subArray.get(i).equals(mainArray.get(j))) {
                    j++;
                    continue;
                }
                L[i] = j;
                break;
            }
        }
    }

    private void calcR() {
        Arrays.fill(R, NOT_ALLOCATED);
        int i = subArray.size() - 1;
        int j = mainArray.size() - 1;
        for (; i >= 0; i--) {
            while (true) {
                if (j == -1) {
                    break;
                }
                if (!subArray.get(i).equals(mainArray.get(j))) {
                    j--;
                    continue;
                }
                R[i] = j;
                break;
            }
        }
    }

    public void solve() {
        calcL();
        calcR();
        int answer = 0;
        if (R[1] != NOT_ALLOCATED) {
            answer++;
        }
        if (L[subArray.size() - 2] != NOT_ALLOCATED) {
            answer++;
        }
        for (int i = 1; i < subArray.size() - 1; i++) {
            if (L[i - 1] == NOT_ALLOCATED || R[i + 1] == NOT_ALLOCATED) {
                continue;
            }
            if (L[i - 1] < R[i + 1]) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
