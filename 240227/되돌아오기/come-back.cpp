#include <iostream>

using namespace std;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, -1, 0, 1};

int main() {
    int N;
    cin >> N;

    int currentX = 0;
    int currentY = 0;

    int directionIndex = -1;
    int time = 0;

    while(N--){
        char direction;
        int moveLength;
        cin >> direction >> moveLength;
        if(direction == 'N'){
            directionIndex = 3;
        }
        if(direction == 'E'){
            directionIndex = 0;
        }
        if(direction == 'W'){
            directionIndex = 2;
        }
        if(direction == 'S'){
            directionIndex = 1;
        }
        bool canEscape = false;
        while(moveLength--){
            time++;
            currentX += dx[directionIndex];
            currentY += dy[directionIndex];
            if(currentX == 0 && currentY == 0){
                canEscape = true;
                break;
            }
        }
        if(canEscape){
            break;
        }
    }

    if(time == 0){
        cout <<  -1;
        return 0;
    }

    cout << time;
    return 0;
}