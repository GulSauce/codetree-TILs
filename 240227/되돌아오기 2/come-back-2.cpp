#include <iostream>
#include <string>

using namespace std;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, -1, 0, 1};

int main() {
    string commands;
    cin >> commands;

    int currentX = 0;
    int currentY = 0;
    int directionIndex = 3;

    int ans = -1;
    int elapsedTime = 0;
    for(char command : commands){
        elapsedTime++;
        if(command == 'L'){
            directionIndex = (directionIndex - 1 + 4) % 4;
        }
        if(command == 'R'){
            directionIndex = (directionIndex + 1) % 4;
        }
        if(command == 'F'){
            currentX += dx[directionIndex];
            currentY += dy[directionIndex];
        }
        if(currentX == 0 && currentY == 0){
            ans = elapsedTime;
            break;
        }
    }

    cout << ans;
    return 0;
}