import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N, M;
        ArrayList<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(M, numbers).solve();
    }
}

class Solver {
    int repeatCount;
    ArrayList<Integer> numbers;
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    public Solver(
            int repeatCount,
            ArrayList<Integer> numbers
    ) {
        this.repeatCount = repeatCount;
        this.numbers = numbers;
    }

    public void solve() {
        for (Integer number : numbers) {
            priorityQueue.add(-number);
        }
        for (int i = 0; i < repeatCount; i++) {
            int maxNumber = -priorityQueue.poll();
            priorityQueue.add(-(maxNumber - 1));
        }
        System.out.println(-priorityQueue.peek());
    }
}