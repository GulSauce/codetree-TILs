#include <iostream>

using namespace std;

char tiles[200000];

void moveLeft(int& currentPosition, int moveCount){
    while(moveCount--){
        tiles[currentPosition--] = 'W';
    }
    currentPosition++;
}

void moveRight(int& currentPosition, int moveCount){
    while(moveCount--){
        tiles[currentPosition++] = 'B';
    }
    currentPosition--;
}

int main() {
    int n;
    cin >> n;
    int currentPosition = 100000;
    while(n--){
        int moveCount;
        char direction;
        cin >> moveCount >> direction;
        if(direction == 'L'){
            moveLeft(currentPosition, moveCount);
        }
        if(direction == 'R'){
            moveRight(currentPosition, moveCount);
        }
    }

    int w = 0, b = 0;
    for(int i = 0; i <= 199999; i++){
        if(tiles[i] == 'W'){
            w++;
        }
        if(tiles[i] == 'B'){
            b++;
        }
    }
    cout << w << ' ' << b;
    return 0;
}