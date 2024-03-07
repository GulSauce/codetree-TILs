#include <iostream>

using namespace std;

int main() {
    int X;
    cin >> X;

    if(X <= 3){
        cout << X;
        return 0;
    }

    for(int t = 2; t <= X; t += 2){
        int area = 1*t + t*t/4 + 1;
        if(area == X){
            cout << t + 1;
            break;
        }

        int nextT = t+2;
        int nextArea = nextT + nextT*nextT/4 + 1;
        
        if(X + 1 <= nextArea){
            cout << nexT + 1;
            break;
        }
    }
    return 0;
}