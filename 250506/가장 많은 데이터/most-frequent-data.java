import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        ArrayList<String> strings = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            strings.add(sc.next());
        }
        sc.close();

        new Solver(strings).solve();
    }
}

class Solver {
    ArrayList<String> strings;

    public Solver(
            ArrayList<String> strings
    ) {
        this.strings = strings;
    }

    public void solve() {
        HashMap<String, Integer> hashMap = new HashMap<>();

        int answer = 0;
        for (String string : strings) {
            if (hashMap.containsKey(string)) {
                hashMap.put(string, hashMap.get(string) + 1);
            } else {
                hashMap.put(string, 1);
            }
            answer = Math.max(answer, hashMap.get(string));
        }
        System.out.println(answer);
    }
}