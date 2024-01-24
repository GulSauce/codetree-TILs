#include <iostream>

using namespace std;

int main() {
    int num[10];
    int sum = 0;
    int cnt = 0;
    for(int i = 0; i < 10; i++){
        cin >> num[i];
        if (num[i] == 0){
            break;
        }
        if(num[i] % 2 != 0){
            continue;
        }
        sum += num[i];
        cnt++;
    }

    cout << cnt << ' ' << sum;
    return 0;
}