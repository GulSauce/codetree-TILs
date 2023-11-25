#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int y = 0; y < n; y++){
        for(int x = 0; x <= y; x++)
            cout << "* ";
        cout << '\n';
    }
    for(int y = n-2; y >= 0; y--){
        for(int x = 0; x <= y; x++)
            cout << "* ";
        cout << '\n';
    }
    return 0;
}