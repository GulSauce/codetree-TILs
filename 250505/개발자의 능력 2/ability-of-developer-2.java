import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] abilities = new int[6];

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            abilities[i] = sc.nextInt();
        }
        sc.close();

        new Solver(abilities).solve();
    }
}

class Solver {
    int abilitiesIndex;
    int[] abilities;

    public Solver(
            int[] abilities
    ) {
        this.abilities = abilities;
        this.abilitiesIndex = abilities.length - 1;
    }

    public void solve() {
        int answer = Integer.MAX_VALUE;
        int abilitySum = getAbilitySum();
        for (int i = 0; i <= abilitiesIndex; i++) {
            for (int j = i + 1; j <= abilitiesIndex; j++) {
                for (int k = 0; k <= abilitiesIndex; k++) {
                    for (int l = k + 1; l <= abilitiesIndex; l++) {
                        if (i == k || i == l || j == k || j == l) {
                            continue;
                        }
                        int team1Ability = abilities[i] + abilities[j];
                        int team2Ability = abilities[k] + abilities[l];
                        int team3Ability = abilitySum - team1Ability - team2Ability;
                        int max = Math.max(Math.max(team1Ability, team2Ability), team3Ability);
                        int min = Math.min(Math.min(team1Ability, team2Ability), team3Ability);
                        answer = Math.min(answer, max - min);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private int getAbilitySum() {
        int sum = 0;
        for (int number : abilities) {
            sum += number;
        }
        return sum;
    }
}