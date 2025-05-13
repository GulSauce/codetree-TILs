import java.util.ArrayList;
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
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    public Solver(
            ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        for (Integer number : numbers) {
            priorityQueue.add(number);
            if (priorityQueue.size() < 3) {
                System.out.println(-1);
                continue;
            }
            int min1 = priorityQueue.poll();
            int min2 = priorityQueue.poll();
            int min3 = priorityQueue.poll();
            System.out.println(min1 * min2 * min3);
            priorityQueue.add(min1);
            priorityQueue.add(min2);
            priorityQueue.add(min3);
        }
    }

}