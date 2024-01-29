#include <iostream>

using namespace std;

int main() {
    int matrix[5][5];
    for(int y = 0; y <= 4; y++){
        matrix[y][0]= 1;
    }

    for(int x = 0; x <= 4; x++){
        matrix[0][x]= 1;
    }
    
    for(int y = 1; y <= 4; y++){
        for(int x = 1 ; x <= 4; x++){
            matrix[y][x] = matrix[y-1][x] + matrix[y][x-1];
        }
    }

    for(int y = 0; y <= 4; y++){
        for(int x = 0 ; x <= 4; x++){
            cout << matrix[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}