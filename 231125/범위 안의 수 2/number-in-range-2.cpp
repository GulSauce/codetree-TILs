#include <iostream>

using namespace std;

int main() {
    int sum = 0, cnt = 0;
    for(int i = 1; i <= 10; i++){
        int num; cin >> num;
        if(0 <= num && num <= 200){
            sum += num;
            cnt ++;
        }
    }

    cout << fixed;
    cout.precision(1);

    cout << sum << ' ' << (double)sum / cnt;
    return 0;
}