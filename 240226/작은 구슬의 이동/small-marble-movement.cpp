#include <iostream>

using namespace std;

int dx[4] = {1, 0, 0, -1};
int dy[4] = {0, 1, -1, 0};

int n, t;

bool checkIsInBorder(int x, int y){
    return x == 0 || x == n+1 || y == 0 || y == n+1;
}

int main() {
    cin >> n >> t;
    int r, c;
    char d;
    cin >> r >> c >> d;

    int currentDirectionIndex = -1;
    if(d == 'U'){
        currentDirectionIndex = 1;
    }
    if(d == 'D'){
        currentDirectionIndex = 2; 
    }
    if(d == 'R'){
        currentDirectionIndex = 0;
    }
    if(d == 'L'){
        currentDirectionIndex = 3;
    }

    while(t--){
        c += dx[currentDirectionIndex];
        r += dy[currentDirectionIndex];
        if(checkIsInBorder(c, r)){
            currentDirectionIndex = 3 - currentDirectionIndex;
            c += dx[currentDirectionIndex];
            r += dy[currentDirectionIndex];
        }
    }

    cout << r << ' ' << c;
    return 0;
}