import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        List<Integer> heights = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            heights.add(x);
        }
        sc.close();

        new Solver(heights).solve();
    }
}

class Solver {
    List<Integer> heights;

    public Solver(
            List<Integer> heights
    ) {
        this.heights = heights;
    }

    public void solve() {
        int answer = Integer.MAX_VALUE;
        for (int minHeight = 1; minHeight <= 1000; minHeight++) {
            int cur = 0;
            int maxHeight = minHeight + 17;
            for (Integer height : heights) {
                int diff;
                if (height < minHeight) {
                    diff = minHeight - height;
                } else if (maxHeight < height) {
                    diff = height - maxHeight;
                } else {
                    diff = 0;
                }
                cur += diff * diff;
            }
            answer = Math.min(answer, cur);
        }
        System.out.println(answer);
    }
}