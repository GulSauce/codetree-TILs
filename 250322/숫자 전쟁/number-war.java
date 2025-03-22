import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Solver {

        int cardsCount;
        final int NOT_ALLOCATED = Integer.MIN_VALUE;

        List<Integer> firstPlayerCards;
        List<Integer> secondPlayerCards;

        int[][] dp;

        public Solver(
            int N,
            List<Integer> firstPlayerCards,
            List<Integer> secondPlayerCards
        ) {
            this.cardsCount = N;
            this.firstPlayerCards = firstPlayerCards;
            this.secondPlayerCards = secondPlayerCards;
            this.dp = new int[N + 1][N + 1];
        }

        public void solve() {
            initDP();
            calcDP();
            printAnswer();
        }

        private void printAnswer() {
            int answer = 0;
            for (int first = 0; first <= cardsCount; first++) {
                answer = Math.max(answer, dp[first][cardsCount]);
            }
            for (int second = 0; second <= cardsCount; second++) {
                answer = Math.max(answer, dp[cardsCount][second]);
            }
            answer = Math.max(answer, dp[cardsCount][cardsCount]);
            System.out.println(answer);
        }

        private void calcDP() {
            for (int first = 0; first < cardsCount; first++) {
                for (int second = 0; second < cardsCount; second++) {
                    if (dp[first][second] == NOT_ALLOCATED) {
                        continue;
                    }

                    int firstCard = firstPlayerCards.get(first + 1);
                    int secondCard = secondPlayerCards.get(second + 1);
                    dp[first + 1][second + 1] = Math.max(dp[first + 1][second + 1],
                        dp[first][second]);
                    
                    if (firstCard == secondCard) {
                        dp[first + 1][second + 1] = Math.max(dp[first + 1][second + 1],
                            dp[first][second]);
                    }
                    if (firstCard < secondCard) {
                        dp[first + 1][second] = Math.max(dp[first + 1][second], dp[first][second]);
                    }
                    if (secondCard < firstCard) {
                        dp[first][second + 1] = Math.max(dp[first][second + 1],
                            dp[first][second] + secondCard);
                    }
                }
            }
        }

        private void initDP() {
            for (int[] array : dp) {
                Arrays.fill(array, NOT_ALLOCATED);
            }
            dp[0][0] = 0;
        }
    }

    public static void main(String[] args) {
        int N;
        List<Integer> firstPlayerCards = new ArrayList<>();
        List<Integer> secondPlayerCards = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        firstPlayerCards.add(-1);
        for (int i = 0; i < N; i++) {
            firstPlayerCards.add(sc.nextInt());
        }
        secondPlayerCards.add(-1);
        for (int i = 0; i < N; i++) {
            secondPlayerCards.add(sc.nextInt());
        }
        sc.close();

        new Main.Solver(N, firstPlayerCards, secondPlayerCards).solve();
    }
}