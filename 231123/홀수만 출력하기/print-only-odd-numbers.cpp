#include <iostream>

using namespace std;

int main() {
    int n; cin >> n;
    for(int i = 1; i <= n; i++){
        int num; cin >> num;
        if(num % 2 == 1 && num % 3 == 0)
            cout << num << '\n';
    }
    return 0;
}