#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 0; i < n; i++){
        for(int b = 0; b < n - i - 1; b++)
            cout << "  ";
        for(int s = 0; s < i*2+1; s++)
            cout << "* ";
        cout << '\n';
    }
    return 0;
}