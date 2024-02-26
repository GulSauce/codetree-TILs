#include <iostream>
#include <string>

using namespace std;

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, -1, 0, 1};

int main() {
    string commands;
    cin >> commands;
    int curDirection = 3;
    int curX = 0;
    int curY = 0;
    for(char command : commands){
        if(command == 'L'){
            curDirection = (curDirection - 1) % 4 + 4;
        }
        if(command == 'R'){
             curDirection = (curDirection  + 1) % 4;
        }
        if(command == 'F'){
            curX += dx[curDirection];
            curY += dy[curDirection];
        }
    }

    cout << curX << ' ' << curY;
    return 0;
}