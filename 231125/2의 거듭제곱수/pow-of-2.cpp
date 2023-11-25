#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int cnt = 0;
    while(1){
        n /= 2;
        cnt++;
        if(n == 1)
            break;
    }

    cout << cnt;
    return 0;
}