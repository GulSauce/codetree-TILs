#include <iostream>

using namespace std;

int main() {
    int matrix[10][10];
    
    int increaseVal = 1;

    int n; cin >> n;
    bool upCase = n % 2;
    for(int x = n-1; x >= 0; x--){
        if(x % 2 != upCase){
            for(int y = 0; y <= n-1; y++){
                matrix[y][x] = increaseVal++;
            }
        }

        if(x % 2 == upCase){
            for(int y = n-1; y >= 0; y--){
                matrix[y][x] = increaseVal++;
            }
        }
    }

    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= n-1; x++){
            cout << matrix[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}