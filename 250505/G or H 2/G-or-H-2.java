import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int N;
        char[] personAlphabets;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        personAlphabets = new char[101];

        for (int i = 0; i < N; i++) {
            int personPos = sc.nextInt();
            char alphabet = sc.next().charAt(0);
            personAlphabets[personPos] = alphabet;
        }
        sc.close();

        new Solver(personAlphabets).solve();
    }
}

class Solver {
    int personAlphabetsIndex;
    char[] personAlphabets;

    public Solver(
            char[] personAlphabets
    ) {
        this.personAlphabets = personAlphabets;
        this.personAlphabetsIndex = personAlphabets.length - 1;
    }

    public void solve() {
        int maxDist = 0;
        for (int start = 0; start <= personAlphabetsIndex; start++) {
            for (int end = start; end <= personAlphabetsIndex; end++) {
                if (isPersonNotExistAtBorder(start, end)) {
                    continue;
                }
                if (isValid(start, end)) {
                    maxDist = Math.max(maxDist, end - start);
                }
            }
        }
        System.out.println(maxDist);
    }

    private boolean isPersonNotExistAtBorder(int start, int end) {
        return personAlphabets[start] == 0 || personAlphabets[end] == 0;
    }

    private boolean isValid(int start, int end) {
        int gCount = 0;
        int hCount = 0;

        for (int i = start; i <= end; i++) {
            if (personAlphabets[i] == 'G') {
                gCount++;
            }
            if (personAlphabets[i] == 'H') {
                hCount++;
            }
        }

        if (gCount == 0 && 0 < hCount) {
            return true;
        }
        if (hCount == 0 && 0 < gCount) {
            return true;
        }
        if (hCount == gCount) {
            return true;
        }
        return false;
    }
}