#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 1; i <= n; i++){
        for(int j = 0; j < n; j++){
            if(j % 2 == 0)
                cout << i;
            else
                cout << n - i + 1;
        }
        cout << '\n';
    }
    return 0;
}