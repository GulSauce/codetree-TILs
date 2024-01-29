#include <iostream>

using namespace std;

int main() {
    int matrix[10][10];

    int n; cin >> n;
    for(int y = 0; y <= n-1; y++){
        matrix[y][0] = 1;
    }
    for(int x = 0; x <= n-1; x++){
        matrix[0][x] = 1;
    }

    for(int y = 1; y <= n-1; y++){
        for(int x = 1; x <= n-1; x++){
            matrix[y][x] = 
                matrix[y-1][x] 
                + matrix[y][x-1]
                + matrix[y-1][x-1];
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