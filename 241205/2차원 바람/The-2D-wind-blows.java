import java.util.*;

public class Main {
    private static class Solver {
        int[][] matrix;
        WindInfo[] windInfos;
        final  int MAX_ROW_INDEX;
        final  int MAX_COLUMN_INDEX;

        public  Solver(
                int[][] matrix,
                WindInfo[] windInfos
        ){
            this.matrix = matrix;
            this.windInfos = windInfos;
            MAX_ROW_INDEX = matrix.length-1;
            MAX_COLUMN_INDEX = matrix[0].length-1;
        }

        public void  solve(){
            for(WindInfo windInfo: windInfos){
                doCycleShift(windInfo.leftAboveCoordinate, windInfo.rightBelowCoordinate);
                changeValue(windInfo.leftAboveCoordinate, windInfo.rightBelowCoordinate);
            }

            printMatrix();
        }

        private void printMatrix(){
            for(int y = 1; y <= MAX_ROW_INDEX; y++){
                for(int x = 1; x <= MAX_COLUMN_INDEX; x++){
                    System.out.printf("%d ", matrix[y][x]);
                }
                System.out.println();
            }
        }

        private void doCycleShift(Coordinate leftAboveCoordinate, Coordinate rightBelowCoordinate){
            Deque<Integer> spreadMatrix = getSpreadMatrix(leftAboveCoordinate, rightBelowCoordinate);
            spreadMatrix.addFirst(spreadMatrix.pollLast());
            for(int x = leftAboveCoordinate.x; x <= rightBelowCoordinate.x; x++){
                matrix[leftAboveCoordinate.y][x] = spreadMatrix.pollFirst();
            }
            for(int y = leftAboveCoordinate.y+1; y <= rightBelowCoordinate.y; y++){
                matrix[y][rightBelowCoordinate.x] = spreadMatrix.pollFirst();
            }
            for(int x = rightBelowCoordinate.x-1; x >= leftAboveCoordinate.x; x--){
                matrix[rightBelowCoordinate.y][x] = spreadMatrix.pollFirst();
            }
            for(int y = rightBelowCoordinate.y-1; y >= leftAboveCoordinate.y+1; y--){
                matrix[y][leftAboveCoordinate.x] = spreadMatrix.pollFirst();
            }
        }

        private void changeValue(Coordinate leftAboveCoordinate, Coordinate rightBelowCoordinate){
            Queue<Integer> valuesToChange = getValuesToChange(leftAboveCoordinate, rightBelowCoordinate);
            for(int y = leftAboveCoordinate.y; y <= rightBelowCoordinate.y; y++){
                for(int x = leftAboveCoordinate.x; x <= rightBelowCoordinate.x; x++){
                    matrix[y][x] = valuesToChange.poll();
                }
            }
        }

        private Queue<Integer> getValuesToChange(Coordinate leftAboveCoordinate, Coordinate rightBelowCoordinate) {
            Queue<Integer> valuesToChange = new LinkedList<>();
            for(int y = leftAboveCoordinate.y; y <= rightBelowCoordinate.y; y++){
                for(int x = leftAboveCoordinate.x; x <= rightBelowCoordinate.x; x++){
                    valuesToChange.add(getCrossAverage(y, x));
                }
            }
            return valuesToChange;
        }

        private int getCrossAverage(int y, int x){
            int sum = matrix[y][x];
            int numberCount = 1;
            if(isXinRage(x-1)){
                numberCount++;
                sum += matrix[y][x-1];
            }
            if(isXinRage(x+1)){
                numberCount++;
                sum += matrix[y][x+1];
            }
            if(isYinRage(y-1)){
                numberCount++;
                sum += matrix[y-1][x];
            }
            if(isYinRage(y+1)){
                numberCount++;
                sum += matrix[y+1][x];
            }
            return sum/numberCount;
        }

        private boolean isXinRage(int x){
            return 1 <= x && x <= MAX_COLUMN_INDEX;
        }

        private boolean isYinRage(int y){
            return 1 <= y && y <= MAX_ROW_INDEX;
        }

        private Deque<Integer> getSpreadMatrix(Coordinate leftAboveCoordinate, Coordinate rightBelowCoordinate){
            Deque<Integer> spreadMatrix = new ArrayDeque<>();
            for(int x = leftAboveCoordinate.x; x <= rightBelowCoordinate.x; x++){
                spreadMatrix.add(matrix[leftAboveCoordinate.y][x]);
            }
            for(int y = leftAboveCoordinate.y+1; y <= rightBelowCoordinate.y; y++){
                spreadMatrix.add(matrix[y][rightBelowCoordinate.x]);
            }
            for(int x = rightBelowCoordinate.x-1; x >= leftAboveCoordinate.x; x--){
                spreadMatrix.add(matrix[rightBelowCoordinate.y][x]);
            }
            for(int y = rightBelowCoordinate.y-1; y >= leftAboveCoordinate.y+1; y--){
                spreadMatrix.add(matrix[y][leftAboveCoordinate.x]);
            }

            return spreadMatrix;
        }
    }

    private static class  WindInfo{
        Coordinate leftAboveCoordinate;
        Coordinate rightBelowCoordinate;

        public WindInfo(
                Coordinate leftAboveCoordinate,
                Coordinate rightBelowCoordinate
        ){
            this.leftAboveCoordinate = leftAboveCoordinate;
            this.rightBelowCoordinate = rightBelowCoordinate;
        }

    }

    private static class Coordinate{
        int x;
        int y;

        public  Coordinate(
                int x,
                int y
        ){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();
        final int Q = sc.nextInt();

        int[][] matrix = new int[N+1][M+1];
        for(int y = 1; y <= N; y++){
            for(int x= 1; x <= M; x++){
                matrix[y][x] = sc.nextInt();
            }
        }

        WindInfo[] windInfos = new WindInfo[Q];
        for(int i = 0; i < Q; i++){
            int r1 = sc.nextInt();
            int c1 = sc.nextInt();
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();
            windInfos[i] = new WindInfo(new Coordinate(c1, r1), new Coordinate(c2, r2));
        }

        new Solver(matrix, windInfos).solve();
    }
}