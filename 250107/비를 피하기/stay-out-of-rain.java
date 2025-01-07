import java.util.*;

public class Main {
    private static class Solver {
        int gridIndex;

        final int WALL = 1;
        final int PERSON = 2;
        final int RAIN_GUARD = 3;

        int[] dRow = {0, -1, 0, 1};
        int[] dCol = {1, 0, -1, 0};

        List<Coordinate> people = new ArrayList<>();

        boolean[][] visited;
        int[][] dist;
        int[][] grid;
        int[][] answer;

        public Solver(
                int[][] grid
        ) {
            this.grid = grid;
            this.gridIndex = grid.length-1;
            this.dist = new int[gridIndex+1][gridIndex+1];
            this.answer = new int[gridIndex+1][gridIndex+1];
            this.visited = new boolean[gridIndex+1][gridIndex+1];
        }

        public void solve(){
            people = getPeople();
            bfsEachPerson();
            printResult();
        }

        private void bfsEachPerson(){
            for(Coordinate person: people){
                initVisited();
                initDist();
                bfs(person);
            }
        }

        private void bfs(Coordinate start){
            int elapsedTime = -1;

            Queue<Coordinate> q = new LinkedList<>();
            dist[start.row][start.col] = 0;
            visited[start.row][start.col] = true;
            q.add(start);
            while(!q.isEmpty()){
                Coordinate prev = q.poll();
                boolean found = false;
                for(int i = 0; i < 4; i++){
                    Coordinate cur = new Coordinate(prev.row+dRow[i], prev.col+dCol[i]);
                    if(isOutOfRange(cur)){
                        continue;
                    }
                    if(isVisited(cur)){
                        continue;
                    }
                    if(isWall(cur)){
                        continue;
                    }

                    dist[cur.row][cur.col] = dist[prev.row][prev.col] + 1;
                    visited[cur.row][cur.col] = true;
                    q.add(cur);
                    if(isRainGuard(cur)){
                        elapsedTime = dist[cur.row][cur.col];
                        found = true;
                        break;
                    }
                }
                if(found){
                    break;
                }
            }
            answer[start.row][start.col] = elapsedTime;
        }

        private boolean isOutOfRange(Coordinate coordinate){
            return coordinate.row < 0 || gridIndex < coordinate.row || coordinate.col < 0 || gridIndex < coordinate.col;
        }

        private boolean isVisited(Coordinate coordinate){
            return visited[coordinate.row][coordinate.col];
        }

        private boolean isWall(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] == WALL;
        }

        private boolean isRainGuard(Coordinate coordinate){
            return grid[coordinate.row][coordinate.col] == RAIN_GUARD;
        }

        private void printResult(){
            for(int[] array: answer){
                for(int value: array){
                    System.out.printf("%d ", value);
                }
                System.out.println();
            }
        }

        private void initDist(){
            for(int[] array: dist){
                Arrays.fill(array, -1);
            }
        }

        private void initVisited(){
            for(boolean[] array: visited){
                Arrays.fill(array, false);
            }
        }

        private List<Coordinate> getPeople(){
            List<Coordinate> people = new ArrayList<>();

            for(int row = 0; row <= gridIndex; row++){
                for(int col = 0; col <= gridIndex; col++){
                    if(grid[row][col] == PERSON){
                        people.add(new Coordinate(row, col));
                    }
                }
            }

            return people;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int h = sc.nextInt();
        int m = sc.nextInt();

        int[][] grid = new int[n][n];
        for(int row = 0; row < n; row++){
            for(int col = 0; col < n; col++){
                grid[row][col] = sc.nextInt();
            }
        }

        new Solver(grid).solve();
    }

    private static class Coordinate{
        int row;
        int col;

        public Coordinate(
                int row,
                int col
        ){
            this.row = row;
            this.col = col;
        }
    }
}