#include <iostream>

using namespace std;

char board[100][100];

int n, m;
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

bool isInRange(int x, int y){
    return 0 <= x && x <= m-1
    && 0 <= y && y <= n-1;
}

void drawAlphabet(){
    int directionIndex = 0;
    int loopCount = n * m;
    char value = 'A';

    int x = 0;
    int y = 0;
    board[y][x] = value;

    for(int i = 0; i <= loopCount - 2; i++){
        while(true){
            int testX = x + dx[directionIndex];
            int testY = y + dy[directionIndex];

            if(!isInRange(testX, testY) || board[testY][testX]){
                directionIndex = (directionIndex + 1) % 4;
                continue;
            }
            break;
        }

        if(value == 'Z'){
            value = 'A';
        }
        else{
            value++;
        }

        x += dx[directionIndex];
        y += dy[directionIndex];

        board[y][x] = value;
    }
}

int main() {
    cin >> n >> m;

    drawAlphabet();

    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= m-1; x++){
            cout << board[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}