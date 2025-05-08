import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<String> A = new ArrayList<>();
        ArrayList<String> B = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            A.add(sc.next());
        }
        for (int i = 0; i < N; i++) {
            B.add(sc.next());
        }
        sc.close();

        new Solver(A, B).solve();
    }
}

class Solver {
    int groupElementIndex;
    int stringIndex;
    ArrayList<String> A;
    ArrayList<String> B;

    public Solver(
            ArrayList<String> A,
            ArrayList<String> B
    ) {
        this.stringIndex = A.get(0).length() - 1;
        this.groupElementIndex = A.size() - 1;
        this.A = A;
        this.B = B;
    }

    public void solve() {
        int answer = 0;
        for (int i = 0; i <= stringIndex; i++) {
            for (int j = i + 1; j <= stringIndex; j++) {
                for (int k = j + 1; k <= stringIndex; k++) {
                    HashSet<String> hashSetA = new HashSet<>();
                    HashSet<String> hashSetB = new HashSet<>();
                    for (int cur = 0; cur <= groupElementIndex; cur++) {
                        String curStringA = A.get(cur);
                        hashSetA.add(String.valueOf(new char[]{curStringA.charAt(i), curStringA.charAt(j), curStringA.charAt(k)}));

                        String curStringB = B.get(cur);
                        hashSetB.add(String.valueOf(new char[]{curStringB.charAt(i), curStringB.charAt(j), curStringB.charAt(k)}));
                    }


                    boolean unique = true;
                    for (String string : hashSetA) {
                        if (hashSetB.contains(string)) {
                            unique = false;
                            break;
                        }
                    }
                    if (!unique) {
                        continue;
                    }
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}
