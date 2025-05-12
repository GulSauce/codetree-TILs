import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextInt());
        }
        sc.close();

        new Solver(numbers).solve();
    }
}

class Solver {
    ArrayList<Integer> numbers;
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

    public Solver(
            ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        priorityQueue.addAll(numbers);
        while (1 < priorityQueue.size()) {
            int first = priorityQueue.poll();
            int second = priorityQueue.poll();
            if (first == second) {
                continue;
            }
            priorityQueue.add(first - second);
        }
        System.out.println(priorityQueue.peek());
    }
}