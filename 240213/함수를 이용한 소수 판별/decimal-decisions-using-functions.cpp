#include <iostream>

using namespace std;

bool isPrime(int number){
    if(number == 1){
        return false;
    }
    for(int i = 2; i <= number-1; i++){
        if(number % i == 0){
            return false;
        }
    }
    return true;
}

int main() {
    int a, b; cin >> a >> b;

    int sumValue = 0;
    for(int i = a; i <= b; i++){
        if(isPrime(i)){
            sumValue += i;
        }
    }

    cout << sumValue;
    return 0;
}