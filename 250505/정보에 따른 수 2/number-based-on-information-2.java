import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int T, a, b;
        int[] alphabets = new int[1001];
        char c;
        int x;

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();
        for (int i = 0; i < T; i++) {
            c = sc.next().charAt(0);
            x = sc.nextInt();
            alphabets[x] = c;
        }
        sc.close();

        new Solver(a, b, alphabets).solve();
    }
}

class Solver {

    int start;
    int end;
    int alphabetsIndex;
    int[] alphabets;

    public Solver(
            int a,
            int b,
            int[] alphabets
    ) {
        this.start = a;
        this.end = b;
        this.alphabets = alphabets;
        this.alphabetsIndex = alphabets.length - 1;
    }

    public void solve() {
        int answer = 0;
        for (int k = start; k <= end; k++) {
            int sDistMin = getSDistMinFrom(k);
            int nDistMin = getNDistMinFrom(k);
            if (sDistMin <= nDistMin) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private int getNDistMinFrom(int k) {
        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i <= alphabetsIndex; i++) {
            if (alphabets[i] != 'N') {
                continue;
            }
            minDist = Math.min(Math.abs(k - i), minDist);
        }
        return minDist;
    }

    private int getSDistMinFrom(int k) {
        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i <= alphabetsIndex; i++) {
            if (alphabets[i] != 'S') {
                continue;
            }
            minDist = Math.min(Math.abs(k - i), minDist);
        }
        return minDist;
    }
}

