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
        for (int length = 1; length <= string.length() - 1; length++) {
            boolean hasSubString = false;
            for (int i = 0; i <= string.length() - length; i++) {
                String subString = string.substring(i, i + length - 1);
                if (i == 0) {
                    String right = string.substring(i + length, string.length() - 1);
                    if (right.contains(subString)) {
                        hasSubString = true;
                        break;
                    }
                    continue;
                }
                if (i == string.length() - length) {
                    String left = string.substring(0, i - 1);
                    if (left.contains(subString)) {
                        hasSubString = true;
                        break;
                    }
                    continue;
                }
                String right = string.substring(i + length, string.length() - 1);
                String left = string.substring(0, i - 1);
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
                System.out.println(length);
                return;
            }
        }
    }
}