import java.util.*;

public class Main {
    private static class  Solver{
        Stack<Integer>[][]  matrix;
        final int MAX_INDEX;
        int[] moveNumbers;

        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

        public Solver(
                Stack<Integer>[][] matrix,
                int[] moveNumbers
        ){
            this.matrix = matrix;
            this.moveNumbers = moveNumbers;
            this.MAX_INDEX = matrix.length-1;
        }

        public void solve(){
            for(int moveNumber: moveNumbers){
                Coordinate moveNumberCoordinate = getMoveNumberCoordinate(moveNumber);
                Coordinate nearGreatestNumberCoordinate = getNearGreatestNumberCoordinate(moveNumberCoordinate);
                move(moveNumber, moveNumberCoordinate, nearGreatestNumberCoordinate);
            }
            printResult();
        }

        private void printResult(){
            for(int y = 0; y <= MAX_INDEX; y++){
                for(int x = 0; x <= MAX_INDEX; x++){
                    if(matrix[y][x].isEmpty()){
                        System.out.print("None");
                    }
                    while(!matrix[y][x].isEmpty()){
                        System.out.printf("%d ", matrix[y][x].pop());
                    }
                    System.out.println();
                }
            }
        }

        private void move(int target, Coordinate source, Coordinate dest){
            Stack<Integer> temp = new Stack<>();
            for(int i = matrix[source.y][source.x].size()-1; i >=0; i--){
                int number = matrix[source.y][source.x].pop();
                temp.add(number);
                if(number == target){
                    break;
                }
            }
            while(!temp.isEmpty()){
                matrix[dest.y][dest.x].add(temp.pop());
            }
        }

        private Coordinate getNearGreatestNumberCoordinate(Coordinate coordinate){
            int greatestNumberCandidate = -1;
            Coordinate nearGreatestNumberCoordinate = new Coordinate(-1, -1);
            for(int i = 0; i < 8; i++){
                Coordinate nextCoordinate= new Coordinate(coordinate.y + dy[i], coordinate.x + dx[i]);
                if(isOutOfRange(nextCoordinate)){
                    continue;
                }
                if(matrix[nextCoordinate.y][nextCoordinate.x].isEmpty()){
                    continue;
                }
                int number = getGreatestNumber(nextCoordinate);
                if(nearGreatestNumberCoordinate.y == -1 && nearGreatestNumberCoordinate.x == -1){
                    nearGreatestNumberCoordinate = nextCoordinate;
                    greatestNumberCandidate = number;
                    continue;
                }
                if(greatestNumberCandidate < number){
                    nearGreatestNumberCoordinate = nextCoordinate;
                    greatestNumberCandidate = number;
                }
            }
            if(greatestNumberCandidate == -1){
                return coordinate;
            }
            return nearGreatestNumberCoordinate;
        }

        private int getGreatestNumber(Coordinate coordinate){
            int value = 0;
            for(int number: matrix[coordinate.y][coordinate.x]){
                value = Math.max(value, number);
            }
            return value;
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.y < 0 || MAX_INDEX < coordinate.y || coordinate.x < 0 || MAX_INDEX < coordinate.x;
        }

        private Coordinate getMoveNumberCoordinate(int moveNumber){
            for(int y = 0; y < matrix.length; y++){
                for(int x = 0; x < matrix.length; x++){
                    for(int number: matrix[y][x]){
                        if(moveNumber == number){
                            return new Coordinate(y, x);
                        }
                    }
                }
            }

            return new Coordinate(-1, -1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Stack<Integer>[][] matrix = (Stack<Integer>[][]) new Stack[n][n];

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                matrix[y][x] = new Stack<Integer>();
            }
        }

        for(int y = 0; y < n; y++){
            for(int x = 0; x < n; x++){
                matrix[y][x].add(sc.nextInt());
            }
        }

        int[] moveNumbers = new int[m];
        for(int i =0; i < m; i++){
            moveNumbers[i] = sc.nextInt();
        }

        new Solver(matrix, moveNumbers).solve();
    }

    private static class Coordinate{
        int y;
        int x;

        public Coordinate(
                int y,
                int x
        ){
            this.y = y;
            this.x = x;
        }
    }
}