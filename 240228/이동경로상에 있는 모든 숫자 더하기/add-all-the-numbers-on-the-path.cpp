#include <iostream>
#include <string>

using namespace std;

int N, T;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int board[100][100];

bool isInRange(int x, int y){
    return 0 <= x && x <= N-1 && 0 <= y && y <= N-1;
}

int main() {
    cin >> N >> T;

    string commands;
    cin >> commands;

    for(int y = 0; y <= N-1; y++){
        for(int x = 0; x <= N-1; x++){
            cin >> board[y][x];
        }
    }

    int x = N/2;
    int y = N/2;

    int value = board[y][x];
    int directionIndex = 3;

    for(char command : commands){
        if(command == 'L'){
            directionIndex = (directionIndex - 1 + 4) % 4;
        }
        if(command == 'R'){
            directionIndex = (directionIndex + 1) % 4;
        }
        if(command == 'F'){
            int testX = x + dx[directionIndex];
            int testY = y + dy[directionIndex];
            if(isInRange(testX, testY) == false){
                continue;
            }

            x += dx[directionIndex];
            y += dy[directionIndex];
            value += board[y][x]; 
        }
    }

    cout << value;
    return 0;
}