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
            boolean hasDuplicatedSubString = false;

            for (int i = 0; i <= string.length() - length; i++) {
                String subString = string.substring(i, i + length);
                int firstIndex = string.indexOf(subString);
                int lastIndex = string.lastIndexOf(subString);
                if (firstIndex != lastIndex) {
                    hasDuplicatedSubString = true;
                    break;
                }
            }
            if (!hasDuplicatedSubString) {
                answer = length;
                break;
            }
        }
        System.out.println(answer);
    }
}