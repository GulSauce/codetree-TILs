#include <iostream>

using namespace std;

int main() {
    int n, m; cin >> n >> m;

    int matrix[11][11] = {};
    while(m--){
        int r, c; cin >> r >> c;
        matrix[r][c] = r * c;
    }

    for(int y= 1; y <= n; y++){
        for(int x = 1; x<=n; x++){
            cout << matrix[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}