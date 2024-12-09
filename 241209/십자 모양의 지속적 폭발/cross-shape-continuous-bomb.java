import java.util.*;

public class Main {
    private static class Solver{
        int[][] matrix;
        final int MAX_R;
        int[] explodeColumns;

        public Solver(
                int[][] matrix,
                int[] explodeColumns
        ){
            this.matrix = matrix;
            this.explodeColumns = explodeColumns;
            MAX_R = matrix.length-1;
        }

        public void solve(){
            for(int explodeColum: explodeColumns){
                explodeFindingNumber(explodeColum);
            }
            printResult();
        }

        private void printResult(){
            for(int r = 1; r <= MAX_R; r++){
                for(int c = 1; c <= MAX_R; c++){
                    System.out.printf("%d ", matrix[r][c]);
                }
                System.out.println();
            }
        }

        private void explodeFindingNumber(int explodeColumn){
            for(int r = 1; r <= MAX_R; r++){
                if(matrix[r][explodeColumn] == 0){
                    continue;
                }
                explodeAt(new Coordinate(r, explodeColumn));
                break;
            }
            applyGravity();
        }

        private void applyGravity(){
            for(int c = 1; c <= MAX_R; c++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int r = MAX_R; r >= 1; r--) {
                    if (matrix[r][c] == 0) {
                        continue;
                    }
                    temp.add(matrix[r][c]);
                }
                for (int i = temp.size(); i <= MAX_R; i++) {
                    temp.add(0);
                }
                for (int r = MAX_R; r >= 1; r--) {
                    matrix[r][c] = temp.get(MAX_R - r);
                }
            }
        }

        private void explodeAt(Coordinate coordinate){
            int centerR = coordinate.r;
            int centerC = coordinate.c;

            int explodeScale = matrix[centerR][centerC];

            for(int r = centerR; r > centerR - explodeScale; r--){
                if(isOutOfRange(r)){
                    break;
                }
                matrix[r][centerC] = 0;
            }

            for(int r = centerR; r < centerR + explodeScale; r++){
                if(isOutOfRange(r)){
                    break;
                }
                matrix[r][centerC] = 0;
            }

            for(int c = centerC; c > centerC - explodeScale; c--){
                if(isOutOfRange(c)){
                    break;
                }
                matrix[centerR][c] = 0;
            }

            for(int c = centerC; c < centerC + explodeScale; c++){
                if(isOutOfRange(c)){
                    break;
                }
                matrix[centerR][c] = 0;
            }
        }

        private boolean isOutOfRange(int value){
            return !(1 <= value && value <= MAX_R);
        }

        private static class Coordinate{
            int r;
            int c;

            public Coordinate(
                    int r,
                    int c
            ){
                this.r = r;
                this.c = c;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int m = sc.nextInt();
        int[][] matrix = new int[n+1][n+1];
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= n; c++){
                matrix[r][c] = sc.nextInt();
            }
        }
        int[] explodeColumns = new int[m];
        for(int i = 0; i < m; i++){
            explodeColumns[i] = sc.nextInt();
        }

        new Solver(matrix, explodeColumns).solve();
    }
}