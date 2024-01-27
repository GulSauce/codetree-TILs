#include <iostream>

using namespace std;

int main() {
    int n, m; cin >> n >> m;
    int nums[100][100];
    int increaseNum = 1;

    for(int y = 0; y < n; y++){
        for(int x = 0; x < m; x++){
            nums[y][x] = increaseNum++;
        }
    }

    for(int y = 0; y < n; y++){
        for(int x = 0; x <  m; x++){
            cout << nums[y][x] << ' ';
        }
        cout << '\n';
    }
    return 0;
}