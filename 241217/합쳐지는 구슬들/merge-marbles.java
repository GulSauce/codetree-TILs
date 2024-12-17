import java.util.*;

public class Main {
    private static class Solver{
        final int MAX_INDEX;
        final int NOT_EXIST_STATUS = -1;

        int[][] existingMarbleIndexMapper;
        ArrayList<Marble> marbles;
        ArrayList<Marble> nextMarbles = new ArrayList<>();
        int MAX_TIME;

        HashMap<Character, Integer> directionMapper;
        HashMap<Character, Character> reverseDirectionMapper;


        int[] dr = {0, -1, 0, 1};
        int[] dc = {1, 0, -1, 0};

        public Solver(
                int[][] existingMarbleIndexMapper,
                ArrayList<Marble> marbles,
                int t
        ){
            this.existingMarbleIndexMapper = existingMarbleIndexMapper;
            this.marbles = marbles;
            this.MAX_TIME = t;
            this.MAX_INDEX = existingMarbleIndexMapper.length-1;
        }

        public void solve(){
            initDirectionIndexMapper();
            initReverseDirectionMapper();
            for(int t = 1; t <= MAX_TIME; t++){
                initNextMarbles();
                initExistingMarbleIndexMapper();
                moveMarblesWithMerging();
            }
            printResult();
        }

        private void printResult(){
            Collections.sort(marbles);
            System.out.printf("%d %d", marbles.size(), marbles.get(0).weight);
        }

        private void initExistingMarbleIndexMapper(){
            for(int[] array: existingMarbleIndexMapper){
                Arrays.fill(array, NOT_EXIST_STATUS);
            }
        }

        private void initNextMarbles(){
            nextMarbles.clear();
        }

        private void moveMarblesWithMerging(){
            for(int i = 0; i < marbles.size(); i++){
                Marble marble = marbles.get(i);
                move(marble);
                if(isMarbleAlreadyExist(marble.coordinate)){
                    mergeMarble(marble);
                    continue;
                }
                int currentR = marble.coordinate.r;
                int currentC = marble.coordinate.c;
                nextMarbles.add(marble);
                existingMarbleIndexMapper[currentR][currentC] = nextMarbles.size()-1;
            }
            marbles = (ArrayList<Marble>) nextMarbles.clone();
        }

        private void mergeMarble(Marble marble){
            int currentR = marble.coordinate.r;
            int currentC = marble.coordinate.c;
            int existingMarbleIndex = existingMarbleIndexMapper[currentR][currentC];
            Marble existingMarble = nextMarbles.get(existingMarbleIndex);
            int weightSum = existingMarble.weight + marble.weight;
            int largestNumber;
            Character direction;
            if(existingMarble.number < marble.number) {
                largestNumber = marble.number;
                direction = marble.direction;
            }
            else{
                largestNumber = existingMarble.number;
                direction = existingMarble.direction;
            }
            nextMarbles.set(existingMarbleIndex, new Marble(largestNumber, marble.coordinate, direction, weightSum));
        }

        private boolean isMarbleAlreadyExist(Coordinate coordinate){
            if(existingMarbleIndexMapper[coordinate.r][coordinate.c] == NOT_EXIST_STATUS){
                return false;
            }
            return true;
        }

        private void move(Marble marble){
            int directionIndex = directionMapper.get(marble.direction);
            int currentR = marble.coordinate.r;
            int currentC = marble.coordinate.c;
            int nextR = currentR + dr[directionIndex];
            int nextC = currentC + dc[directionIndex];
            if(isOutOfIndex(nextR) || isOutOfIndex(nextC)){
                marble.direction = reverseDirectionMapper.get(marble.direction);
            }
            else{
                marble.coordinate = new Coordinate(nextR, nextC);
            }
        }

        private boolean isOutOfIndex(int index){
            return index <= 0 || MAX_INDEX < index;
        }

        private void initDirectionIndexMapper(){
            directionMapper = new HashMap<Character, Integer>();
            directionMapper.put('R', 0);
            directionMapper.put('U', 1);
            directionMapper.put('L', 2);
            directionMapper.put('D', 3);
        }

        private void initReverseDirectionMapper(){
            reverseDirectionMapper = new HashMap<Character, Character>();
            reverseDirectionMapper.put('R', 'L');
            reverseDirectionMapper.put('U', 'D');
            reverseDirectionMapper.put('L', 'R');
            reverseDirectionMapper.put('D', 'U');
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] existingMarbleIndexMapper = new int[n+1][n+1];

        for(int[] array: existingMarbleIndexMapper){
            Arrays.fill(array, 0);
        }

        int m = sc.nextInt();
        int t = sc.nextInt();

        ArrayList<Marble> marbles = new ArrayList<>();
        for(int i = 0; i < m; i++){
            int r = sc.nextInt();
            int c = sc.nextInt();
            Character d = sc.next().charAt(0);
            int w = sc.nextInt();
            marbles.add(new Marble(i, new Coordinate(r, c) ,d, w));
        }

        new Solver(existingMarbleIndexMapper, marbles, t).solve();
    }

    private static class Marble implements Comparable<Marble>{
        int number;
        Coordinate coordinate;
        Character direction;
        int weight;

        public Marble(
                int i,
                Coordinate coordinate,
                Character d,
                int w
        ){
            this.number = i;
            this.coordinate = coordinate;
            this.direction = d;
            this.weight = w;
        }

        @Override
        public int compareTo(Marble marble){
            return marble.weight - this.weight;
        }
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