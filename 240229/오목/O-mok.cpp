#include <iostream>

using namespace std;

int board[20][20];

int dx[8] = {1, 1, 0, -1, -1, -1, 0, 1};
int dy[8] = {0, 1, 1, -1, 0, -1, 1,  -1};

bool isInRange(int x, int y){
    return 1 <= x && x <= 19 && 1 <= y && y <= 19;
}

int main() {
    for(int y = 1; y <= 19; y++){
        for(int x = 1; x <= 19; x++){
            cin >> board[y][x];
        }
    }
    
    for(int y = 1; y <= 19; y++){
        for(int x = 1; x <= 19; x++){
            int startColor = board[y][x];
            if(startColor == 0){
                continue;
            }
            for(int direction = 0; direction <= 7; direction++){
                int currentX = x;
                int currentY = y;
                int matchCount = 1;
                while(true){
                    int testX = currentX + dx[direction];
                    int testY = currentY + dy[direction];
                    if(!isInRange(testX, testY) 
                    || board[testY][testX] != startColor){
                        break;
                    }
                    currentX += dx[direction]; 
                    currentY += dy[direction];

                    matchCount++;
                }
                if(matchCount == 5){
                    cout << startColor << '\n';
                    cout << y + 2*dy[direction]
                    << ' ' <<  x + 2*dx[direction];
                    return 0;
                }
            }
        }
    }

    cout << 0;
    return 0;
}