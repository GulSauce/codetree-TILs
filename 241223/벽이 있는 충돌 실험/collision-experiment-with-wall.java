import java.util.*;

public class Main {
    private static class Solver {
        private final int gridSize;
        private final List<Marble> marbles;
        private final int[][] board;

        // Direction mappings: R, U, L, D
        private final Map<Character, int[]> directionMap = new HashMap<>();

        public Solver(int gridSize, List<Marble> marbles) {
            this.gridSize = gridSize;
            this.marbles = new ArrayList<>(marbles);
            this.board = new int[gridSize + 2][gridSize + 2]; // Including walls

            // Initialize direction mappings
            directionMap.put('R', new int[]{0, 1});
            directionMap.put('U', new int[]{-1, 0});
            directionMap.put('L', new int[]{0, -1});
            directionMap.put('D', new int[]{1, 0});
        }

        public void solve() {
            // Surround the grid with walls by initializing borders
            for (int i = 0; i <= gridSize + 1; i++) {
                board[0][i] = -1;
                board[gridSize + 1][i] = -1;
                board[i][0] = -1;
                board[i][gridSize + 1] = -1;
            }

            // Place initial marbles on the board
            for (Marble marble : marbles) {
                board[marble.y][marble.x]++;
            }

            // Simulate movements until no changes occur or a maximum number of iterations
            boolean changed;
            int iterations = 0;
            final int MAX_ITERATIONS = 10000; // To prevent infinite loops

            do {
                changed = false;
                iterations++;

                // Prepare a new list for updated marbles
                List<Marble> updatedMarbles = new ArrayList<>();

                // Temporary board to track new positions
                int[][] tempBoard = new int[gridSize + 2][gridSize + 2];
                for (int i = 0; i <= gridSize + 1; i++) {
                    tempBoard[0][i] = -1;
                    tempBoard[gridSize + 1][i] = -1;
                    tempBoard[i][0] = -1;
                    tempBoard[i][gridSize + 1] = -1;
                }

                // Move each marble
                for (Marble marble : marbles) {
                    int[] move = directionMap.get(marble.direction);
                    int newY = marble.y + move[0];
                    int newX = marble.x + move[1];

                    // Check for wall collision
                    if (board[newY][newX] == -1) {
                        // Reverse direction
                        char reversedDirection = reverseDirection(marble.direction);
                        marble.direction = reversedDirection;
                        // Direction change takes 1 second (handled implicitly by iteration)
                        newY = marble.y; // Does not move
                        newX = marble.x;
                        changed = true;
                    } else {
                        // Move to new position
                        marble.y = newY;
                        marble.x = newX;
                        changed = true;
                    }

                    // Update temporary board
                    tempBoard[marble.y][marble.x]++;
                    updatedMarbles.add(marble);
                }

                // Detect and remove collisions
                List<Marble> survivingMarbles = new ArrayList<>();
                for (Marble marble : updatedMarbles) {
                    if (tempBoard[marble.y][marble.x] == 1) {
                        survivingMarbles.add(marble);
                    }
                }

                // Update the marbles list
                marbles.clear();
                marbles.addAll(survivingMarbles);

                // Update the board
                for (int i = 1; i <= gridSize; i++) {
                    for (int j = 1; j <= gridSize; j++) {
                        board[i][j] = tempBoard[i][j];
                    }
                }

                // Terminate if no changes or maximum iterations reached
                if (!changed || iterations >= MAX_ITERATIONS) {
                    break;
                }

            } while (true);

            // Output the number of surviving marbles
            System.out.println(marbles.size());
        }

        private char reverseDirection(char d) {
            switch (d) {
                case 'U':
                    return 'D';
                case 'D':
                    return 'U';
                case 'L':
                    return 'R';
                case 'R':
                    return 'L';
                default:
                    return d;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int testCase = 0; testCase < T; testCase++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            List<Marble> marbles = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                char d = sc.next().charAt(0);
                marbles.add(new Marble(y, x, d));
            }

            Solver solver = new Solver(N, marbles);
            solver.solve();
        }
    }

    private static class Marble {
        int y;
        int x;
        char direction;

        public Marble(int y, int x, char direction) {
            this.y = y;
            this.x = x;
            this.direction = direction;
        }
    }
}