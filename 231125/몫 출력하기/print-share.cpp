#include <iostream>

using namespace std;

int main() {
    int num;
    int cnt = 0;
    while(1){
        cin >> num;
        if(num % 2 == 0){
            cout << num/2 << '\n';
            cnt++;
        }
        if(cnt == 3)
            break;
    }
    return 0;
}