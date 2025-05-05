import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        String string;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        string = sc.next();

        new Solver(string).solve();
    }
}

class Solver {
    String string;

    public Solver(
            String string
    ) {
        this.string = string;
    }

    public void solve() {
        int answer = string.length();
        for (int length = 1; length <= string.length() - 1; length++) {
            boolean hasSubString = false;
            for (int i = 0; i <= string.length() - length; i++) {
                String left = string.substring(0, i);
                String subString = string.substring(i, i + length);
                String right = string.substring(i + length);
                if (right.contains(subString)) {
                    hasSubString = true;
                    break;
                }
                if (left.contains(subString)) {
                    hasSubString = true;
                    break;
                }
            }
            if (!hasSubString) {
                answer = length;
                break;
            }
        }
        System.out.println(answer);
    }
}