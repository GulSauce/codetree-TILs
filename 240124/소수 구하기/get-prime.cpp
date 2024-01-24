#include <iostream>

using namespace std;

bool isPrime(int number){
    for(int i = 2; i < number; i++){
        if(number % i == 0){
            return false;
        }
    }
    return true;
}

int main() {
    int n; cin >> n;
    for(int number = 2; number <= n; number++){
        if(isPrime(number)){
            cout << number << ' ';
        }
    }
    return 0;
}