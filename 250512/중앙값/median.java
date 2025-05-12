import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int T;

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int M;
            M = sc.nextInt();
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                numbers.add(sc.nextInt());
            }
            new Solver(numbers).solve();
        }
    }
}

class Solver {
    ArrayList<Integer> numbers;
    PriorityQueue<Integer> leftHeap = new PriorityQueue<>();
    PriorityQueue<Integer> rightHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public Solver(
            ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        System.out.print(numbers.get(0) + " ");
        if (numbers.get(0) < numbers.get(1)) {
            rightHeap.add(numbers.get(0));
            leftHeap.add(numbers.get(1));
        } else {
            leftHeap.add(numbers.get(0));
            rightHeap.add(numbers.get(1));
        }
        for (int i = 2; i < numbers.size(); i++) {
            int cur = numbers.get(i);
            if (rightHeap.size() < leftHeap.size()) {
                if (leftHeap.peek() < cur) {
                    rightHeap.add(cur);
                } else {
                    leftHeap.add(cur);
                    rightHeap.add(leftHeap.poll());
                }
            } else if (leftHeap.size() < rightHeap.size()) {
                if (rightHeap.peek() < cur) {
                    rightHeap.add(cur);
                    leftHeap.add(rightHeap.poll());
                    leftHeap.add(rightHeap.poll());
                } else {
                    leftHeap.add(cur);
                }
            } else {
                if (leftHeap.peek() < cur) {
                    rightHeap.add(cur);
                    leftHeap.add(rightHeap.poll());
                } else {
                    leftHeap.add(cur);
                }
            }
            if (i % 2 == 0) {
                System.out.print(leftHeap.peek() + " ");
            }
        }
        System.out.println();
    }
}