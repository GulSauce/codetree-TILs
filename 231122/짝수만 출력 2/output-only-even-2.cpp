#include <iostream>

int main() {
    int b, a; cin >> b >> a;
    while(b >= a){
        cout << b << ' ';
        b -= 2;
    }
    return 0;
}