import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        String seatsString;
        char[] seats;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        seatsString = sc.next();
        seats = new char[N];
        for (int i = 0; i < seats.length; i++) {
            seats[i] = seatsString.charAt(i);
        }
        sc.close();

        new Solver(seats).solve();
    }
}

class Solver {
    final char EMPTY = '0';
    final char FULL = '1';

    int seatsIndex;
    char[] seats;

    public Solver(
            char[] seats
    ) {
        this.seatsIndex = seats.length - 1;
        this.seats = seats;
    }

    public void solve() {
        int answer = 0;
        for (int i = 0; i <= seatsIndex; i++) {
            for (int j = i + 1; j <= seatsIndex; j++) {
                if (seats[i] == FULL || seats[j] == FULL) {
                    continue;
                }
                char[] newSeats = Arrays.copyOf(seats, seats.length);
                newSeats[i] = FULL;
                newSeats[j] = FULL;
                int minDist = getMinDistOf(newSeats);
                answer = Math.max(answer, minDist);
            }
        }
        System.out.println(answer);
    }

    private int getMinDistOf(char[] seats) {
        int dist = Integer.MAX_VALUE;
        int prevIndex = -1;
        for (int i = 0; i <= seatsIndex; i++) {
            if (seats[i] == EMPTY) {
                continue;
            }
            if (prevIndex == -1) {
                prevIndex = i;
                continue;
            }
            dist = Math.min(dist, i - prevIndex);
            prevIndex = i;
        }
        return dist;
    }
}