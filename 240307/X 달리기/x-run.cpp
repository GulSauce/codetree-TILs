#include <iostream>

using namespace std;

int main() {
    int X;
    cin >> X;

    if(X <= 2){
        cout << X;
        return 0;
    }

    for(int t = 1; t <= X; t++){
        int area = 1*t + t*t/4;
        if(t % 2 == 1){
            continue;
        }
        if(area == X){
            cout << t;
            break;
        }

        int tPlus1 = t+2;
        int testArea = tPlus1 + tPlus1*tPlus1/4;
        
        if(X + 1 <= testArea){
            cout << t + X - area;
            break;
        }
    }
    return 0;
}