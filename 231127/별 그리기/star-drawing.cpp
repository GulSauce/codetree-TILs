#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 0 ; i < n; i++){
        for(int b = 0; b < n - i - 1; b++)
            cout << ' ';
        for(int s = 0; s < 2 * i + 1; s++)
            cout << '*';
        cout << '\n';
    }
    for(int i = n-1 ; i >= 1; i--){
        for(int b = 0; b < n - i; b++)
            cout << ' ';
        for(int s = 0; s < 2*i-1; s++)
            cout << '*';
        cout << '\n';
    }
    return 0;
}