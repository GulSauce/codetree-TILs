#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int sum = 0, cnt = 0;
    while(n--){
       int n; cin >> n;
        sum += n;
        cnt++;
    }

    cout << fixed;
    cout.precision(1);

    cout << sum << ' ' << (double)sum/cnt;
    return 0;
}