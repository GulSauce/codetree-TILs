#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int start = 'A';
    for(int y = 0; y < n; y++){
        for(int x = 0; x< n; x++){
            cout << (char)start++;
        }
        cout << '\n';
    }
    return 0;
}