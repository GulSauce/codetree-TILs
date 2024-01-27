#include <iostream>

using namespace std;

int main() {
    int matrix1[3][3];
    int matrix2[3][3];

    for(int y = 0; y<= 2; y++){
        for(int x = 0; x<= 2; x++){
            cin >> matrix1[y][x];
        }
    }

    for(int y = 0; y<= 2; y++){
        for(int x = 0; x<= 2; x++){
            cin >> matrix2[y][x];
        }
    }

    for(int y = 0; y<= 2; y++){
        for(int x = 0; x<= 2; x++){
            cout << matrix1[y][x]*matrix2[y][x] << ' ';
        }
        cout << '\n';
    }

    return 0;
}