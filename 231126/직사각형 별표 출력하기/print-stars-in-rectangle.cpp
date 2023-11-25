#include <iostream>

using namespace std;

int main() {
    int n, m; cin >> n >> m;
    for(int y = 1; y <= n; y++){
        for(int x = 1; x <= m; x++)
            cout << "* ";
        cout << '\n';
    }
    return 0;
}