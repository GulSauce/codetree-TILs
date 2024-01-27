#include <iostream>

using namespace std;

int main() {
    int num[4][4];
    for(int y = 0 ; y <= 3; y++){
        for(int x = 0; x <= 3; x++){
            cin >> num[y][x];
        }
    }

    int sumVal = 0 ;
    for(int y = 0; y <= 3; y++){
        for(int x = 0; x <= y; x++){
            sumVal += num[y][x];
        }
    }

    cout << sumVal;
    return 0;
}