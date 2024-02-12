#include <iostream>

using namespace std;

void printGCD(int n, int m){
    int gcd = 1;
    for(int i = 1; i <= 100; i++){
        if(n % i == 0 && m % i == 0){
            gcd = i;
        }
    }
    cout << gcd;
}

int main() {
    int n, m; cin >> n >> m;
    printGCD(n, m);
    return 0;
}