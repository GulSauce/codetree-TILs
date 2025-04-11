import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String A;

        Scanner sc = new Scanner(System.in);
        A = sc.next();
        sc.close();

        new Solver(A).solve();
    }
}

class Solver {

    String A;
    int answer = Integer.MAX_VALUE;
    int lettersIndex;
    char[] letters;

    public Solver(
        String A
    ) {
        this.A = A;
    }

    public void solve() {
        letters = A.toCharArray();
        lettersIndex = letters.length - 1;
        for (int shiftCount = 0; shiftCount <= lettersIndex; shiftCount++) {
            String runLengthString = getRunLengthString();
            answer = Math.min(answer, runLengthString.length());
            shiftRight();
        }
        System.out.println(answer);
    }

    private void shiftRight() {
        char endLetter = letters[lettersIndex];
        for (int i = lettersIndex; i >= 1; i--) {
            letters[i] = letters[i - 1];
        }
        letters[0] = endLetter;
    }

    private String getRunLengthString() {
        StringBuilder runLengthString = new StringBuilder();
        char letter = letters[0];
        int count = 1;
        for (int i = 1; i <= lettersIndex; i++) {
            if (letters[i] == letters[i - 1]) {
                count++;
            } else {
                String sub = String.valueOf(letter) + count;
                runLengthString.append(sub);
                letter = letters[i];
                count = 1;
            }
        }
        String sub = String.valueOf(letter) + count;
        runLengthString.append(sub);

        return runLengthString.toString();
    }
}