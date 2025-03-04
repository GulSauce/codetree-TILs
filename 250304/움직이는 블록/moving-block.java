import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int average;
        int blocksIndex;
        List<Integer> blocks;

        public Solver(
            int N,
            List<Integer> blocks
        ) {
            this.blocksIndex = N - 1;
            this.blocks = blocks;
        }

        public void solve() {
            average = getAverage();
            int answer = 0;
            for (int block : blocks) {
                if (average < block) {
                    answer += block - average;
                }
            }
            System.out.println(answer);
        }

        private int getAverage() {
            int sum = 0;
            for (int block : blocks) {
                sum += block;
            }
            return sum / blocks.size();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> blocks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            blocks.add(sc.nextInt());
        }

        new Solver(N, blocks).solve();
    }
}