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

    int currentX = n / 2;
    int currentY = n / 2;
    int value = 1;
    int lastValue = n*n;

    board[currentY][currentX] = value;

    int directionIndex = 0;

    int maxMoveLength = 1;
    int currentMoveCount = 0;

    for(value = 2; value <= lastValue; value++){
        int moveLengthHasPropertyToIncrease = maxMoveLength;
        if(currentMoveCount == maxMoveLength){
            directionIndex = (directionIndex - 1 + 4) % 4;
            currentMoveCount = 0;
            if(directionIndex == 0 || directionIndex == 2){
                maxMoveLength++;
            }
 
        }
        currentX += dx[directionIndex];
        currentY += dy[directionIndex];
        board[currentY][currentX] = value;
        currentMoveCount++;
    }

    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= n-1; x++){
            cout << board[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}