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
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    ArrayList<Integer> numbers;

    public Solver(
            ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        priorityQueue.add(numbers.get(numbers.size() - 1));
        priorityQueue.add(numbers.get(numbers.size() - 2));
        int currentSum = numbers.get(numbers.size() - 1) + numbers.get(numbers.size() - 2);

        double answer = 0;
        for (int i = numbers.size() - 3; i >= 0; i--) {
            currentSum += numbers.get(i);
            priorityQueue.add(numbers.get(i));
            int value = currentSum - priorityQueue.peek();
            answer = Math.max(answer, (double) value / (priorityQueue.size() - 1));
        }
        System.out.printf("%.2f", answer);
    }
}