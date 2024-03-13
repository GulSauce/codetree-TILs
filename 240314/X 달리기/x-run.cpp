#include <iostream>

using namespace std;

int main() {
    int X;
    cin >> X;

    if(X <= 3){
        cout << X;
        return 0;
    }
    
    if(X == 1 || X ==2){
        cout << 2;
    }

    X--;

    int currentV = 2;
    while(currentV * currentV + 2*currentV <= X){
        currentV++;
    }

    int leftDist = X - (currentV - 1) * (currentV-1) - 2*(currentV-1);

    int offSet = 0;

    if(1 <= leftDist && leftDist <= currentV){
        offSet = 1;
    }
    if(currentV + 1 <= leftDist && leftDist <= 2*currentV){
        offSet = 2;
    }

    cout << (currentV-1)*2 + offSet + 1;
    return 0;
}