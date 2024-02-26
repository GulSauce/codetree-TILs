#include <iostream>

using namespace std;

int dx[4] = {0, 1, -1, 0};
int dy[4] = {1, 0, 0, -1};

int main() {
    int N;
    cin >> N;

    int curX = 0;
    int curY = 0;

    while(N--){
        char direction;
        int moveCount;
        cin >> direction >> moveCount;
        int directionNumber = -1;
        if(direction == 'N'){
            directionNumber = 0;
        }
        if(direction == 'E'){
            directionNumber = 1;
            
        }
        if(direction == 'W'){
            directionNumber = 2;
        }
        if(direction == 'S'){
            directionNumber = 3;
            
        }
        curX += moveCount*dx[directionNumber];
        curY += moveCount*dy[directionNumber];
    }
    cout << curX << ' ' << curY;
    return 0;
}