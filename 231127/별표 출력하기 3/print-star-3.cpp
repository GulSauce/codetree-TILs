#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 0; i < n; i++){
        for(int b = 0; b < i; b++)
            cout << "  ";
        for(int s = 2*n - 1 - i*2; s > 0; s--)
            cout << "* ";
        cout << '\n';
    }
    return 0;
}