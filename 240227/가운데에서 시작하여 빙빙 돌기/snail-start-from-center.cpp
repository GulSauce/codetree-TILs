#include <iostream>

using namespace std;

int board[100][100];

int n;
int dx[4] = {1, 0, -1 ,0};
int dy[4] = {0, 1, 0, -1};

bool isInRange(int x, int y){
    return 0 <= x && x <= n-1
    && 0 <= y && y <= n-1;
}

int main() {
    cin >> n;

    int currentX = n-1;
    int currentY = n-1;
    int value = n*n;

    board[currentY][currentX] = value;

    int directionIndex = 2;

    for(--value; 1 <= value; value--){
        while(true){
            int testX = currentX + dx[directionIndex];
            int testY = currentY + dy[directionIndex];
            if(!isInRange(testX, testY) || board[testY][testX]){
                directionIndex = (directionIndex + 1) % 4;
                continue;
            }
            break;
        }
        currentX += dx[directionIndex];
        currentY += dy[directionIndex];
        board[currentY][currentX] = value;
    }

    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= n-1; x++){
            cout << board[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}