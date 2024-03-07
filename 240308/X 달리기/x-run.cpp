#include <iostream>

using namespace std;

int main() {
    int X;
    cin >> X;

    if(X <= 3){
        cout << X;
        return 0;
    }

    X--;

    for(int t = 1; t <= X; t++){
        if(t % 2 == 1){
            continue;
        }

        int area = 1*t + t*t/4;
        if(area == X){
            cout << t + 1;
            break;
        }

        int tPlus2 = t+2;
        int testArea = tPlus2 + tPlus2*tPlus2/4;
        
        if(X + 1 <= testArea){
            cout << t + 1 + 1;
            break;
        }
    }
    return 0;
}