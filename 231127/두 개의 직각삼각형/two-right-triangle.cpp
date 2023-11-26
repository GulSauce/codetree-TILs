#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 0; i < n; i++){
        for(int s = 0; s < n-i; s++)
            cout << '*';
        for(int b = 0; b < i*2; b++)
            cout << ' ';
        for(int s = 0; s < n-i; s++)
            cout << '*';
        cout << '\n';
    }
    return 0;
}