import javax.swing.text.Style;
import java.util.*;

public class Main {
    private static class Solver{
        int gridSize;
        Set<Coordinate> applePositions;
        Set<Coordinate> snakePositions = new HashSet<>();

        int[] deltaY = {1, 0, -1, 0};
        int[] deltaX = {0, -1, 0, 1};

        private final Map<String, Integer>  directioIndexMap = new HashMap<>();

        MoveInstruction[] moveInstructions;

        int elapsedTime = 0;

        Deque<Coordinate> snakeParts = new ArrayDeque<>();
        public Solver(
                int N,
                Set<Coordinate> applePositions,
                MoveInstruction[] moveInstructions
        ){
            this.gridSize = N;
            this.applePositions = applePositions;
            this.moveInstructions = moveInstructions;
        }

        public void solve(){
            initSnake();
            initDirectionIndexMap();

            for(MoveInstruction moveInstruction : moveInstructions){
                boolean isGameOVer = move(moveInstruction);
                if(isGameOVer){
                    break;
                }
            }

            System.out.println(elapsedTime);
        }

        private boolean move(MoveInstruction moveInstruction){
            int totalMove = moveInstruction.moveCount;
            String direction = moveInstruction.direction;
            int directionIndex = directioIndexMap.get(direction);
            Coordinate head = snakeParts.peekFirst();

            for(int moveCount = 0; moveCount < totalMove; moveCount++){
                elapsedTime++;
                Coordinate nextHead = new Coordinate(head.x+deltaX[directionIndex], head.y+deltaY[directionIndex]);
                if(isCollideSnake(nextHead)){
                    return true;
                }

                snakeParts.addFirst(nextHead);
                snakePositions.add(nextHead);
                head = nextHead;
                if(isOutOfGrid(head)){
                    return true;
                }

                if(applePositions.contains(head)){
                    applePositions.remove(head);
                    continue;
                }

                Coordinate tail = snakeParts.peekLast();
                snakePositions.remove(tail);
                snakeParts.pollLast();
            }
            return false;
        }

        private void printApplePositions(){
            System.out.println("APPLE========");
            for(Coordinate coordinate: applePositions){
                System.out.printf("%d %d\n", coordinate.x, coordinate.y);
            }
            System.out.println("========");
        }

        private void printSnakePositions(){
            System.out.println("SNAKE========");
            for(Coordinate coordinate: snakePositions){
                System.out.printf("%d %d\n", coordinate.x, coordinate.y);
            }
            System.out.println("========");
        }

        private boolean isOutOfGrid(Coordinate coordinate){
            return coordinate.x < 1 || gridSize < coordinate.x || coordinate.y < 1 || gridSize < coordinate.y;
        }

        private boolean isCollideSnake(Coordinate coordinate){
            return snakeParts.contains(coordinate);
        }

        private void initSnake(){
            Coordinate start = new Coordinate(1, 1);
            snakeParts.add(start);
            snakePositions.add(start);
        }

        private void initDirectionIndexMap(){
            directioIndexMap.put("R", 0);
            directioIndexMap.put("U", 1);
            directioIndexMap.put("L", 2);
            directioIndexMap.put("D", 3);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        Set<Coordinate> apples = new HashSet<>();
        for(int i = 0; i < M; i++){
            apples.add(new Coordinate(sc.nextInt(), sc.nextInt()));
        }

        MoveInstruction[] moveInstructions = new MoveInstruction[K];
        for(int i = 0; i < K; i++){
            moveInstructions[i] = new MoveInstruction(sc.next(), sc.nextInt());
        }

        new Solver(N, apples, moveInstructions).solve();
    }

    private static class MoveInstruction {
        String direction;
        int moveCount;

        public MoveInstruction(
                String d,
                int p
        ){
            this.direction = d;
            this.moveCount = p;
        }
    }

    private static class Coordinate{
        int x;
        int y;

        public Coordinate(
                int x,
                int y
        ){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj){
            if(this == obj) return true;
            if(!(obj instanceof Coordinate)) return true;
            Coordinate other = (Coordinate) obj;
            return this.x == other.x && this.y == other.y;
        }

        public int hashCode(){
            return Objects.hash(x, y);
        }
    }
}