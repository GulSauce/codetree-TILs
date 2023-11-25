#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    int sumVal = 0;
    while(n--){
        int num; cin >> num;
        if(num % 3 == 0 && num % 2 == 1)
            sumVal += num;
    }

    cout << sumVal;
    return 0;
}