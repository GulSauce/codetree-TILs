import java.util.*;

public class Main {
    private static class Solver{
        int[][] board;
        final int MAX_BOARD_INDEX;
        Character[] rollCommands;
        Coordinate currentCoordinate;
        Dice dice = new Dice();

        public  Solver(
                int[][] board,
                Character[] rollCommands,
                Coordinate startCoordinate
        ){
            this.board = board;
            this.rollCommands = rollCommands;
            this.MAX_BOARD_INDEX = board.length-1;
            this.currentCoordinate = startCoordinate;
        }

        public  void solve(){
            board[currentCoordinate.r][currentCoordinate.c] = dice.bottomValue;
            for(Character rollCommand: rollCommands){
                rollDice(rollCommand);
                board[currentCoordinate.r][currentCoordinate.c] = dice.bottomValue;
            }
            printResult();
        }

        private void printResult(){
            int result = 0;
            for(int[] array: board){
                for(int value: array){
                    result += value;
                }
            }
            System.out.println(result);
        }

        private void rollDice(Character command){
            if(command.equals('L')){
                int nextR = currentCoordinate.r;
                int nextC = currentCoordinate.c-1;
                if(isOutOfRange(nextR, nextC)){
                    return;
                }
                dice.rollLeft();
                currentCoordinate = new Coordinate(nextR, nextC);
            }
            if(command.equals('R')){
                int nextR = currentCoordinate.r;
                int nextC = currentCoordinate.c+1;
                if(isOutOfRange(nextR, nextC)){
                    return;
                }
                dice.rollRight();
                currentCoordinate = new Coordinate(nextR, nextC);
            }
            if(command.equals('U')){
                int nextR = currentCoordinate.r - 1;
                int nextC = currentCoordinate.c;
                if(isOutOfRange(nextR, nextC)){
                    return;
                }
                dice.rollUp();
                currentCoordinate = new Coordinate(nextR, nextC);
            }
            if(command.equals('D')){
                int nextR = currentCoordinate.r + 1;
                int nextC = currentCoordinate.c;
                if(isOutOfRange(nextR, nextC)){
                    return;
                }
                dice.rollDown();
                currentCoordinate = new Coordinate(nextR, nextC);
            }
        }

        private boolean isOutOfRange(int r, int c){
            return !((1 <= r && r <= MAX_BOARD_INDEX) && (1 <= c && c <= MAX_BOARD_INDEX));
        }

        private static class Dice{
            int bottomValue = 6;
            int leftValue = 4;
            int rightValue = 3;
            int frontValue = 2;
            int backValue = 5;
            int upValue = 1;

            public void rollLeft(){
                int copiedBottomValue = bottomValue;
                int copiedLeftValue = leftValue;
                int copiedRightValue = rightValue;
                int copiedFrontValue = frontValue;
                int copiedBackValue = backValue;
                int copiedUpValue = upValue;

                bottomValue = copiedLeftValue;
                leftValue = copiedUpValue;
                frontValue = copiedFrontValue;
                rightValue = copiedBottomValue;
                backValue = copiedBackValue;
                upValue = copiedRightValue;
            }

            public void rollRight(){
                int copiedBottomValue = bottomValue;
                int copiedLeftValue = leftValue;
                int copiedRightValue = rightValue;
                int copiedFrontValue = frontValue;
                int copiedBackValue = backValue;
                int copiedUpValue = upValue;

                bottomValue = copiedRightValue;
                leftValue = copiedBottomValue;
                frontValue = copiedFrontValue;
                rightValue = copiedUpValue;
                backValue = copiedBackValue;
                upValue = copiedLeftValue;
            }

            public void rollUp(){
                int copiedBottomValue = bottomValue;
                int copiedLeftValue = leftValue;
                int copiedRightValue = rightValue;
                int copiedFrontValue = frontValue;
                int copiedBackValue = backValue;
                int copiedUpValue = upValue;

                bottomValue = copiedBackValue;
                leftValue = copiedLeftValue;
                frontValue = copiedBottomValue;
                rightValue = copiedRightValue;
                backValue = copiedUpValue;
                upValue = copiedFrontValue;
            }

            public void rollDown(){
                int copiedBottomValue = bottomValue;
                int copiedLeftValue = leftValue;
                int copiedRightValue = rightValue;
                int copiedFrontValue = frontValue;
                int copiedBackValue = backValue;
                int copiedUpValue = upValue;

                bottomValue = copiedFrontValue;
                leftValue = copiedLeftValue;
                frontValue = copiedUpValue;
                rightValue = copiedRightValue;
                backValue = copiedBottomValue;
                upValue = copiedBackValue;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] board = new int[n+1][n+1];
        for(int[] array: board){
            Arrays.fill(array, 0);
        }
        Character[] rollCommands = new Character[m];
        for(int i = 0; i < m; i++){
              String command = sc.next();
              rollCommands[i] = command.charAt(0);
        }

        new Solver(board, rollCommands, new Coordinate(r, c)).solve();
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