#include <iostream>

using namespace std;

int main() {
    int n, m; cin >> n >> m;

    int matrix[100][100];
    int increaseNum = 0;
    for(int x = 0; x <= m-1; x++){
        if(x % 2 == 0){
            for(int y = 0; y <= n-1; y++){
                matrix[y][x] = increaseNum++;
            }
        }
        if(x % 2 == 1){
            for(int y = n-1; y >= 0; y--){
                matrix[y][x] = increaseNum++;
            }
        }
    }

    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= m-1; x++){
            cout << matrix[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}