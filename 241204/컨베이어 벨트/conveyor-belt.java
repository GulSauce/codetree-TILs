import java.util.*;

public class Main {
    private static class Solver {
        int[][] conveyor;
        int t;

        public  Solver(
                int[][] conveyor,
                int t
        ){
            this.conveyor = conveyor;
            this.t = t;
        }

        public void  solve(){
            for(int i = 0; i < t; i++){
                moveConveyor();
            }
            printConveyor();
        }

        private void printConveyor() {
            final int LAST_INDEX = conveyor[0].length-1;
            for (int j = 0; j <= LAST_INDEX; j++) {
                System.out.printf("%d ", conveyor[0][j]);
            }
            System.out.println();
            for (int j = LAST_INDEX; j >= 0; j--) {
                System.out.printf("%d ", conveyor[1][j]);
            }
        }

        private  void moveConveyor(){
            int valueToUp = conveyor[1][0];

            int[] belowConveyor = conveyor[1];
            for(int n = 1; n < conveyor[1].length; n++){
                belowConveyor[n-1] = belowConveyor[n];
            }

            int[] upperConveyor = conveyor[0];
            int valueToDown = conveyor[0][conveyor[0].length-1];
            for(int n = conveyor[0].length-1; n > 0; n--){
                upperConveyor[n] = upperConveyor[n-1];
            }

            conveyor[0][0] = valueToUp;
            conveyor[1][conveyor[1].length-1] = valueToDown;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int t = sc.nextInt();
        int[][] conveyor = new int[2][n];
        for(int j = 0; j < n; j++){
            conveyor[0][j] = sc.nextInt();
        }
        for(int j = n-1; j >= 0; j--){
            conveyor[1][j] = sc.nextInt();
        }

        new Solver(conveyor, t).solve();
    }
}