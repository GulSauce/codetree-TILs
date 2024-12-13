import java.util.*;

public class Main {
    private static class Solver{
        int[][] matrix;
        int[][] currentBidCounts;
        final int MAX_INDEX;
        Coordinate[] bidCoordinates;
        int time;

        public Solver(
                int[][] matrix,
                Coordinate[] bidCoordinates,
                int time
        ){
            this.matrix = matrix;
            MAX_INDEX = matrix.length-1;
            this.currentBidCounts = new int[MAX_INDEX+1][MAX_INDEX+1];
            this.bidCoordinates = bidCoordinates;
            this.time = time;
        }

        public void solve(){
            initCurrentBidCounts();
            for(int i = 0; i < time; i++){
                setUpNextBid();
            }
            printResult();
        }

        private void printResult() {
            int result = 0;
            for (int y = 1; y <= MAX_INDEX; y++) {
                for (int x = 1; x <= MAX_INDEX; x++) {
                    if (currentBidCounts[y][x] == 1) {
                        result++;
                    }
                }
            }
            System.out.print(result);
        }

        private void setUpNextBid(){
            int[][] nextBidCounts = new int[MAX_INDEX+1][MAX_INDEX+1];
            for(int y = 1; y <= MAX_INDEX; y++){
                for(int x = 1; x <= MAX_INDEX; x++){
                    nextBidCounts[y][x] = 0;
                }
            }
            for(int r = 1; r <= MAX_INDEX; r++){
                for(int c= 1; c <= MAX_INDEX; c++){
                    if(currentBidCounts[r][c] == 0){
                        continue;
                    }
                    Coordinate nextCoordinate = findLargestCoordinateAt(new Coordinate(r, c));
                    nextBidCounts[nextCoordinate.r][nextCoordinate.c]++;
                }
            }
            currentBidCounts = nextBidCounts;
            removeAbove2();
        }

        private void removeAbove2(){
            for(int y = 1; y <= MAX_INDEX; y++){
                for(int x = 1; x <= MAX_INDEX; x++){
                    if(2 <= currentBidCounts[y][x]) {
                        currentBidCounts[y][x] = 0;
                    }
                }
            }
        }

        Coordinate findLargestCoordinateAt(Coordinate coordinate){
            Coordinate largestCoordinate = new Coordinate(-1, -1);
            if(isInRange(new Coordinate(coordinate.r-1, coordinate.c))){
                if(largestCoordinate.r == -1 && largestCoordinate.c == -1){
                    largestCoordinate = new Coordinate(coordinate.r-1, coordinate.c);
                }
                else{
                    if(matrix[largestCoordinate.r][largestCoordinate.c] < matrix[coordinate.r-1][coordinate.c]){
                        largestCoordinate = new Coordinate(coordinate.r-1, coordinate.c);
                    }
                }
            }
            if(isInRange(new Coordinate(coordinate.r+1, coordinate.c))){
                if(largestCoordinate.r == -1 && largestCoordinate.c == -1){
                    largestCoordinate = new Coordinate(coordinate.r+1, coordinate.c);
                }
                else{
                    if(matrix[largestCoordinate.r][largestCoordinate.c] < matrix[coordinate.r+1][coordinate.c]){
                        largestCoordinate = new Coordinate(coordinate.r+1, coordinate.c);
                    }
                }
            }
            if(isInRange(new Coordinate(coordinate.r, coordinate.c-1))){
                if(largestCoordinate.r == -1 && largestCoordinate.c == -1){
                    largestCoordinate = new Coordinate(coordinate.r, coordinate.c-1);
                }
                else{
                    if(matrix[largestCoordinate.r][largestCoordinate.c] < matrix[coordinate.r][coordinate.c-1]){
                        largestCoordinate = new Coordinate(coordinate.r, coordinate.c-1);
                    }
                }
            }
            if(isInRange(new Coordinate(coordinate.r, coordinate.c+1))){
                if(largestCoordinate.r == -1 && largestCoordinate.c == -1){
                    largestCoordinate = new Coordinate(coordinate.r, coordinate.c+1);
                }
                else{
                    if(matrix[largestCoordinate.r][largestCoordinate.c] < matrix[coordinate.r][coordinate.c+1]){
                        largestCoordinate = new Coordinate(coordinate.r, coordinate.c+1);
                    }
                }
            }
            return largestCoordinate;
        }

        private boolean isInRange(Coordinate coordinate){
            return (1 <= coordinate.r && coordinate.r <= MAX_INDEX) && (1 <= coordinate.c && coordinate.c <= MAX_INDEX);
        }

        private  void initCurrentBidCounts(){
            for(int r = 1; r <= MAX_INDEX; r++){
                for(int c= 1; c <= MAX_INDEX; c++){
                    currentBidCounts[r][c] = 0;
                }
            }
            for(Coordinate bidCoordinate: bidCoordinates){
                currentBidCounts[bidCoordinate.r][bidCoordinate.c]++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int t = sc.nextInt();

        int[][] matrix = new int[n+1][n+1];

        for(int r = 1; r <= n; r++){
            for(int c = 1; c <=n; c++){
                matrix[r][c] = sc.nextInt();
            }
        }

        Coordinate[] bidCoordinates = new Coordinate[m];
        for(int i = 0; i < m; i++){
            int r = sc.nextInt();
            int c = sc.nextInt();
            bidCoordinates[i] = new Coordinate(r, c);
        }

        new Solver(matrix, bidCoordinates, t).solve();
    }

    private static  class  Coordinate{
        int r;
        int c;
        public  Coordinate(
                int r,
                int c
        ){
            this.r = r;
            this.c = c;
        }
    }
}