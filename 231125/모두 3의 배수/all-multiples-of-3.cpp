#include <iostream>

using namespace std;

int main() {
    bool flag = true;
    for(int i = 1; i <= 5; i++){
        int num; cin >> num;
        if(num % 3 != 0)
            flag = false;
    }
    cout << flag;
    return 0;
}