#include <iostream>

using namespace std;

void printByArags(int n, int m){
    for(int y = 0; y <= n-1; y++){
        for(int x = 0; x <= m-1; x++){
            cout << 1;
        }
        cout << '\n';
    }
}

int main() {
    int n, m; 
    cin >> n >> m;
    printByArags(n ,m);
    return 0;
}