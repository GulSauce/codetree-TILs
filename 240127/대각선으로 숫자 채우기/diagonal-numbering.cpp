#include <iostream>

using namespace std;

int main() {
    int n, m; cin >> n >> m;
    int num[100][100];
    int increaseVal = 1;
    for(int x = 0; x <= m-1; x++){
        for(int i = 0; i <= x; i++){
            if(n <= i){
                break;
            }
            num[i][x-i] = increaseVal++;
        }
    }

    for(int y = 1; y <= n-1; y++){
        for(int i = 0; i <= n-y-1; i++){
            num[y+i][m-i-1] = increaseVal++;
        }
    }

    for(int y = 0; y <= n-1; y++){
        for(int x = 0;  x <= m-1; x++){
            cout << num[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}