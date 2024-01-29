#include <iostream>

using namespace std;

int main() {
    int pascalTriangle[15][15] = {};

    int n; cin >> n;

    for(int y = 0; y <= n-1; y++){
        pascalTriangle[y][0] = 1;
    }

    for(int y = 1; y <= n-1; y++){
        for(int x = 1; x <= y; x++){
            pascalTriangle[y][x] = 
                pascalTriangle[y-1][x-1] +  pascalTriangle[y-1][x];
        }
    }

    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= y; x++){
            cout << pascalTriangle[y][x] << ' ';
        }
        cout << '\n';
    }

    return 0;
}