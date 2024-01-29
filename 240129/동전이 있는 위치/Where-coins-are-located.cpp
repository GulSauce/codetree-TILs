#include <iostream>

using namespace std;

int main() {
    int n,  m; cin >> n >> m;
    int coins[10][10] = {};
    while(m--){
        int r, c; cin >> r >> c;
        coins[r][c] = 1;
    }
    for(int y = 1; y <= n; y++){
        for(int x = 1; x <= n; x++){
            cout << coins[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}