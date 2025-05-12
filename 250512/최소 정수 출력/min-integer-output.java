import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        int x;
        ArrayList<Integer> numbers = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            x = sc.nextInt();
            numbers.add(x);
        }
        sc.close();

        new Solver(numbers).solve();
    }
}

class Solver {
    ArrayList<Integer> numbers;
    PriorityQueue<Integer> priorityQueue =new PriorityQueue<>();

    public Solver(
            ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        for (Integer number : numbers) {
            if (0 < number) {
                priorityQueue.add(number);
                continue;
            }
            if (priorityQueue.isEmpty()) {
                System.out.println(0);
                continue;
            }
            System.out.println(priorityQueue.poll());
        }
    }
}