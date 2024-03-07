#include <iostream>

using namespace std;

int main() {
    int X;
    cin >> X;

    if(X <= 3){
        cout << X;
        return 0;
    }
    
    int firstT = 1;
    int firstArea = 1;

    for(int t = 2; t <= X; t += 2){
        int area = t + t*t/4 + firstArea;
        if(area == X){
            cout << t + firstT;
            break;
        }

        int maxV = t/2 + 1;

        if(area + 1 <= X
        && X <= area + maxV){
            cout << t + firstT + 1;
            break;
        }

        int nextT = t+2;
        int nextArea = nextT + nextT*nextT/4 + firstArea;
        
        if(X + 1 <= nextArea){
            cout << t + firstT + 2;
            break;
        }
    }
    return 0;
}