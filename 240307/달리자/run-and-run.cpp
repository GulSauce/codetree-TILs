#include <iostream>

using namespace std;

int main() {
    int n;
    cin >> n;
    
    int A[100];
    for(int i = 0; i <= n-1; i++){
        cin >> A[i];
    }
    
    int B[100];
    for(int i = 0; i <= n-1; i++){
        cin >> B[i];
    }

    int moveLength = 0;
    int C[100] = {};
    for(int i = 0; i <= n-2; i++){
        int currentExist = A[i] + C[i];
        if(B[i] + 1 <= currentExist){
            int movePeople = currentExist - B[i];
            C[i+1] = movePeople;
            moveLength += movePeople;
        }
    }
    cout << moveLength;
    return 0;
}