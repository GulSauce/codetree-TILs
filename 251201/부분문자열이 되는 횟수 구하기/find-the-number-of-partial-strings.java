import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String A;
        String B;
        List<Integer> skipIndexes = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        A = sc.next();
        B = sc.next();
        for (int i = 0; i < A.length(); i++) {
            skipIndexes.add(sc.nextInt());
        }

        new Solver(A, B, skipIndexes).solve();
    }
}

class Solver {

    String A;
    String B;
    List<Integer> skipIndexes;

    public Solver(String a, String b, List<Integer> skipIndexes) {
        A = a;
        B = b;
        this.skipIndexes = skipIndexes;
    }

    public void solve() {
        int left = 0;
        int right = skipIndexes.size() - 1;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isValid(mid)) {
                left = mid + 1;
                answer = Math.max(answer, mid + 1);
            } else {
                right = mid - 1;
            }
        }
        if (isSubString(A, B)) {
            answer++;
        }
        System.out.println(answer);
    }

    private boolean isValid(int mid) {
        StringBuilder sb = new StringBuilder();
        HashSet<Integer> skipIndexHashSet = new HashSet<>();
        for (int i = 0; i <= mid; i++) {
            skipIndexHashSet.add(skipIndexes.get(i));
        }
        for (int i = 0; i < A.length(); i++) {
            if (skipIndexHashSet.contains(i + 1)) {
                continue;
            }
            sb.append(A.charAt(i));
        }
        String realA = sb.toString();
        return isSubString(realA, B);
    }

    private boolean isSubString(String A, String B) {
        int i = 0;
        int j = 0;
        for (; i < A.length(); i++) {
            while (true) {
                if (A.charAt(i) == B.charAt(j)) {
                    j++;
                }
                if (B.length() == j) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
}