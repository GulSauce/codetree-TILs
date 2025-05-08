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
    ArrayList<ArrayList<ArrayList<HashSet<String>>>> aGroupExistString = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<HashSet<String>>>> bGroupExistString = new ArrayList<>();

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
        init();
        initGroup();

        int answer = 0;
        for (int i = 0; i <= stringIndex; i++) {
            for (int j = i + 1; j <= stringIndex; j++) {
                for (int k = j + 1; k <= stringIndex; k++) {
                    HashSet<String> currentAHashSet = aGroupExistString.get(i).get(j).get(k);
                    HashSet<String> currentBHashSet = bGroupExistString.get(i).get(j).get(k);

                    boolean unique = true;
                    for (String string : currentAHashSet) {
                        if (currentBHashSet.contains(string)) {
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

    private void initGroup() {

        for (int i = 0; i <= stringIndex; i++) {
            for (int j = i + 1; j <= stringIndex; j++) {
                for (int k = j + 1; k <= stringIndex; k++) {
                    for (int cur = 0; cur <= groupElementIndex; cur++) {
                        String curString = A.get(cur);
                        aGroupExistString.get(i).get(j).get(k).add(String.valueOf(new char[]{curString.charAt(i), curString.charAt(j), curString.charAt(k)}));
                    }
                }
            }
        }

        for (int i = 0; i <= stringIndex; i++) {
            for (int j = i + 1; j <= stringIndex; j++) {
                for (int k = j + 1; k <= stringIndex; k++) {
                    for (int cur = 0; cur <= groupElementIndex; cur++) {
                        String curString = B.get(cur);
                        bGroupExistString.get(i).get(j).get(k).add(String.valueOf(new char[]{curString.charAt(i), curString.charAt(j), curString.charAt(k)}));
                    }
                }
            }
        }
    }

    private void init() {
        for (int i = 0; i <= stringIndex; i++) {
            aGroupExistString.add(new ArrayList<>());
            for (int j = 0; j <= stringIndex; j++) {
                aGroupExistString.get(i).add(new ArrayList<>());
                for (int k = 0; k <= stringIndex; k++) {
                    aGroupExistString.get(i).get(j).add(new HashSet<>());
                }
            }
        }

        for (int i = 0; i <= stringIndex; i++) {
            bGroupExistString.add(new ArrayList<>());
            for (int j = 0; j <= stringIndex; j++) {
                bGroupExistString.get(i).add(new ArrayList<>());
                for (int k = 0; k <= stringIndex; k++) {
                    bGroupExistString.get(i).get(j).add(new HashSet<>());
                }
            }
        }
    }
}