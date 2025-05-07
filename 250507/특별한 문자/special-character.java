import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String string;

        Scanner sc = new Scanner(System.in);
        string = sc.next();
        sc.close();

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
        char[] stringChars = string.toCharArray();
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (Character character : stringChars) {
            frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
        }

        boolean noOnlyOne = true;
        for (Character character : stringChars) {
            if (frequencyMap.get(character) != 1) {
                continue;
            }
            noOnlyOne = false;
            System.out.println(character);
            break;
        }
        if (noOnlyOne) {
            System.out.println("None");
        }
    }
}