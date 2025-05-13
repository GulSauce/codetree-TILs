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
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> {
        int aAbs = Math.abs(a);
        int bAbs = Math.abs(b);
        if (aAbs == bAbs) {
            return Integer.compare(a, b);
        }
        return Integer.compare(aAbs, bAbs);
    });

    public Solver(
            ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        for (Integer number : numbers) {
            if (number.equals(0)) {
                if(priorityQueue.isEmpty()){
                    System.out.println(0);
                    continue;
                }
                System.out.println(priorityQueue.poll());
                continue;
            }
            priorityQueue.add(number);
        }
    }
}
