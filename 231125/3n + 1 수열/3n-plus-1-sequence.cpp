#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int cnt = 0;
    while(1){
        if(n == 1)
            break;
        cnt ++;
        if(n % 2 == 0)
            n /= 2;
        else if(n % 2 == 1)
            n = n * 3 + 1;
    }

    cout << cnt;
    return 0;
}