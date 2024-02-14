#include <iostream>

using namespace std;

bool isDigitSumIsEven(int number){
    int digitSum = 0;
    while(number){
        digitSum += number % 10;
        number /= 10;
    }
    return digitSum % 2 == 0;
}

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

    int cnt = 0;
    for(int i = a; i <= b; i++){
        if(isDigitSumIsEven(i) && isPrime(i)){
            cnt++;
        }
    }

    cout << cnt;
    return 0;
}