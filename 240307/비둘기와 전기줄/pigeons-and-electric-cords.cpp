#include <iostream>
#include <cstring>

using namespace std;

int main() {
    int N;
    cin >> N;
    
    int moveCount = 0;

    int pigeonPosArray[11];
    memset(pigeonPosArray, -1, sizeof pigeonPosArray);

    while(N--){
        int pigeonNumber, pigeonPos;
        cin >> pigeonNumber >> pigeonPos;
        if(pigeonPosArray[pigeonNumber] != -1
        && pigeonPosArray[pigeonNumber] != pigeonPos){
            moveCount++;
        }
        pigeonPosArray[pigeonNumber] = pigeonPos;
    }

    cout << moveCount;
    return 0;
}