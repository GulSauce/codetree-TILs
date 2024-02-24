#include <iostream>

using namespace std;

pair<int, int> boardColorCount[200000];
char boardColor[200000];

void moveLeft(int& curPos, int moveCount){
    while(moveCount--){
        boardColor[curPos] = 'W';
        boardColorCount[curPos].first++;
        curPos--;
    }
    curPos++;
}

void moveRight(int& curPos, int moveCount){
    while(moveCount--){
        boardColor[curPos] = 'B';
        boardColorCount[curPos].second++;
        curPos++;
    }
    curPos--;
}

int main() {
    int n;
    cin >> n;
    int curPos = 100000;
    while(n--){
        int moveCount;
        char direction;
        cin >> moveCount >> direction;
        if(direction == 'L'){
            moveLeft(curPos, moveCount);
        }
        if(direction == 'R'){
            moveRight(curPos, moveCount);
        }
    }

    int w = 0, b = 0, g = 0;

    for(int i = 0; i <= 199999; i++){
        if(2 <= boardColorCount[i].first && 2 <= boardColorCount[i].second){
            g++;
        }
        else{
            if(boardColor[i] == 'W'){
                w++;
            }
            if(boardColor[i] == 'B'){
                b++;
            }
        }
    }

    cout << w << ' ' << b << ' ' << g;
    return 0;
}