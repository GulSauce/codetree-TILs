#include <iostream>

using namespace std;

int main() {
    int matrix[10][10];

    int n; cin >> n;
    int increaseNum  = 1;
    for(int x = 0; x <= n-1; x++){
        for(int y = 0; y <= n-1; y++){
            matrix[y][x] = increaseNum++;
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