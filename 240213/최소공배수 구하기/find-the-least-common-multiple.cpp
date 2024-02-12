#include <iostream>

using namespace std;

void printLCM(int n, int m){
    for(int i = 1; i <= 10000; i++){
        if(i % n == 0 && i % m == 0){
            cout << i;
            break;
        }
    }
}

int main() {
    int n, m; cin >> n >> m;
    printLCM(n, m);
    return 0;
}