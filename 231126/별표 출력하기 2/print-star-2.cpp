#include <iostream>

using namespace std;

int main() {
    int n; cin  >> n;
    for(int y = 0; y < n; y++){
        for(int x = 0; x < n-y; x++)
            cout << "* ";
        cout << '\n';
    }
    return 0;
}