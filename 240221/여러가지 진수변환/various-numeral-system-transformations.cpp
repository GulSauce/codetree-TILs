#include <iostream>

using namespace std;

int main() {
    int N, B; 
    cin >> N >> B;
    
    int digitCnt = 0;
    int someDigitValue[1000] = {};
    while(N){
        if(N <= B-1){
            someDigitValue[digitCnt++] = N;
            break;
        }
        someDigitValue[digitCnt++] = N % B;
        N /= B;
    }
    for(int i = digitCnt-1; i >= 0; i--){
        cout << someDigitValue[i];
    }
    return 0;
}