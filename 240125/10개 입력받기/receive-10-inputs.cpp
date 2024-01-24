#include <iostream>

using namespace std;

int main() {
    int num[10];
    int end = 9;
    for(int i = 0; i < 10; i++){
        cin >> num[i];
        if(num[i] == 0){
            end = i;
        }
    }

    int sum = 0;
    for(int i = 0; i < end; i++){
        sum += num[i];
    }

    cout << fixed;
    cout.precision(1);
    cout << sum << ' ' << (double)sum/end;

    return 0;
}