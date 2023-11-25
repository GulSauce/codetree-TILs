#include <iostream>

using namespace std;

int main() {
    int a, b; cin >> a >> b;
    bool satisfied = false;
    for(int i = a; i <= b; i++){
        if(1920 % i == 0 && 2880 % i == 0)
            satisfied = true;
    }
    cout << satisfied;
    return 0;
}