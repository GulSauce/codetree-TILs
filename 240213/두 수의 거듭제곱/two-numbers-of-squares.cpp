#include <iostream>

using namespace std;

int getPower(int a, int b){
    int result = 1;
    while(b--){
        result *= a;
    }
    return result;
}

int main() {
    int a, b; cin >> a >> b;
    int power = getPower(a, b);
    cout << power;
    return 0;
}