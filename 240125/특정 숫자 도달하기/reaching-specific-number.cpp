#include <iostream>

using namespace std;

int main() {
    int num[10];
    int sum = 0;
    int cnt = 0;

    for(int i = 0; i < 10; i++){
        cin >> num[i];
    }

    for(int i = 0; i < 10; i++){
        if(250 <= num[i]){
            break;
        }
        sum += num[i];
        cnt++;
    }
    
    cout << fixed;
    cout.precision(1);
    cout << sum << ' ' << (double)sum/cnt;

    return 0;
}