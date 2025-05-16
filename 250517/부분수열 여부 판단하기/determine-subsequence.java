import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int N, M;
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }
        br.close();

        new Solver(A, B).solve();
    }
}

class Solver {

    ArrayList<Integer> A;
    ArrayList<Integer> B;

    public Solver(
        ArrayList<Integer> A,
        ArrayList<Integer> B
    ) {
        this.A = A;
        this.B = B;
    }

    public void solve() {
        int prevJ = -1;
        boolean failed = false;
        for (int i = 0; i < B.size(); i++) {
            while (true) {
                int curJ = prevJ + 1;
                if (A.size() == curJ) {
                    failed = true;
                    break;
                }
                if (A.get(curJ).equals(B.get(i))) {
                    break;
                }
                prevJ = curJ;
            }
            if (failed) {
                break;
            }
            prevJ++;
        }
        if (failed) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }
}