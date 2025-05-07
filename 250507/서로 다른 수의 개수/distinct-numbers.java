import java.util.ArrayList;
import java.util.HashSet;
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

    public Solver(
            ArrayList<Integer> numbers
    ) {
        this.numbers = numbers;
    }

    public void solve() {
        HashSet<Integer> hashSet = new HashSet<>(numbers);
        System.out.println(hashSet.size());
    }
}