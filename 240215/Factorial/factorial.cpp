#include <iostream>

using namespace std;

int getFactorial(int number){
    if(number == 0){
        return 1;
    }

    return getFactorial(number-1) * number; 
}

int main() {
    int N; cin >> N;
    int factorialValue = getFactorial(N);
    cout << factorialValue;
    return 0;
}